const {
  Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell,
  Header, Footer, AlignmentType, HeadingLevel, BorderStyle, WidthType,
  ShadingType, VerticalAlign, PageNumber, PageBreak, LevelFormat,
  ExternalHyperlink, TableOfContents, UnderlineType
} = require('C:/Users/MohamedKoubaa/AppData/Roaming/npm/node_modules/docx');
const fs = require('fs');

// ─── COLORS & FONTS ──────────────────────────────────────────────────────────
const FONT      = "Calibri";
const BLUE_DARK = "1F3864";  // title / hoofdstuk kleur
const BLUE_MID  = "2E5FAC";  // h2 kleur
const BLACK     = "000000";
const GRAY_LIGHT= "F2F2F2";  // tabelrij achtergrond
const GRAY_MED  = "BFBFBF";  // tabelrand

// ─── HELPERS ─────────────────────────────────────────────────────────────────

/** Maak een TextRun met optionele bold-markering */
function styledText(text, opts = {}) {
  return new TextRun({
    text,
    font: FONT,
    size: opts.size || 24,           // 12pt default
    bold: opts.bold || false,
    italics: opts.italic || false,
    color: opts.color || BLACK,
  });
}

/** Gewone alinea */
function body(text, opts = {}) {
  // Verwerk **bold** stukken in de tekst
  const parts = [];
  const regex = /\*\*(.+?)\*\*/g;
  let last = 0, m;
  while ((m = regex.exec(text)) !== null) {
    if (m.index > last) parts.push(styledText(text.slice(last, m.index), opts));
    parts.push(styledText(m[1], { ...opts, bold: true }));
    last = m.index + m[0].length;
  }
  if (last < text.length) parts.push(styledText(text.slice(last), opts));

  return new Paragraph({
    children: parts,
    spacing: { after: 160, line: 276 },
    alignment: AlignmentType.JUSTIFIED,
    indent: opts.indent ? { left: 720 } : undefined,
  });
}

/** Lege regel */
function spacer() {
  return new Paragraph({ children: [styledText("")], spacing: { after: 100 } });
}

/** Hoofdstuk 1 (H1) */
function h1(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_1,
    pageBreakBefore: true,
    children: [new TextRun({ text, font: FONT, size: 36, bold: true, color: BLUE_DARK })],
    spacing: { before: 0, after: 240 },
  });
}

/** Paragraaf-titel (H2) */
function h2(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_2,
    children: [new TextRun({ text, font: FONT, size: 28, bold: true, color: BLUE_MID })],
    spacing: { before: 280, after: 160 },
    border: { bottom: { style: BorderStyle.SINGLE, size: 4, color: BLUE_MID, space: 4 } },
  });
}

/** Citaat / quote */
function quote(text) {
  return new Paragraph({
    children: [styledText(text, { italic: true, color: "444444" })],
    spacing: { before: 80, after: 80 },
    indent: { left: 720, right: 720 },
    border: { left: { style: BorderStyle.SINGLE, size: 12, color: BLUE_MID, space: 8 } },
  });
}

/** Genummerd lijstitem */
function numberedItem(text) {
  const parts = [];
  const regex = /\*\*(.+?)\*\*/g;
  let last = 0, m;
  while ((m = regex.exec(text)) !== null) {
    if (m.index > last) parts.push(styledText(text.slice(last, m.index)));
    parts.push(styledText(m[1], { bold: true }));
    last = m.index + m[0].length;
  }
  if (last < text.length) parts.push(styledText(text.slice(last)));
  return new Paragraph({
    numbering: { reference: "numbers", level: 0 },
    children: parts,
    spacing: { after: 120, line: 276 },
  });
}

/** Bullet lijstitem */
function bulletItem(text) {
  const parts = [];
  const regex = /\*\*(.+?)\*\*/g;
  let last = 0, m;
  while ((m = regex.exec(text)) !== null) {
    if (m.index > last) parts.push(styledText(text.slice(last, m.index)));
    parts.push(styledText(m[1], { bold: true }));
    last = m.index + m[0].length;
  }
  if (last < text.length) parts.push(styledText(text.slice(last)));
  return new Paragraph({
    numbering: { reference: "bullets", level: 0 },
    children: parts,
    spacing: { after: 120, line: 276 },
  });
}

/** Sectie-deelvraag (vetgedrukt, ingespring) */
function deelvraag(text) {
  return new Paragraph({
    children: [styledText(text, { bold: true, italic: true, color: "333333", size: 22 })],
    spacing: { before: 120, after: 120 },
    indent: { left: 360 },
  });
}

// ─── TABELLEN ────────────────────────────────────────────────────────────────

const cellBorder = { style: BorderStyle.SINGLE, size: 4, color: GRAY_MED };
const allBorders = { top: cellBorder, bottom: cellBorder, left: cellBorder, right: cellBorder };

function tableHeaderCell(text, width) {
  return new TableCell({
    borders: allBorders,
    width: { size: width, type: WidthType.DXA },
    shading: { fill: BLUE_DARK, type: ShadingType.CLEAR },
    margins: { top: 80, bottom: 80, left: 120, right: 120 },
    children: [new Paragraph({
      children: [new TextRun({ text, font: FONT, size: 22, bold: true, color: "FFFFFF" })],
    })],
  });
}

function tableDataCell(text, width, shade = false) {
  return new TableCell({
    borders: allBorders,
    width: { size: width, type: WidthType.DXA },
    shading: shade ? { fill: GRAY_LIGHT, type: ShadingType.CLEAR } : undefined,
    margins: { top: 80, bottom: 80, left: 120, right: 120 },
    children: [new Paragraph({
      children: [new TextRun({ text, font: FONT, size: 22, color: BLACK })],
    })],
  });
}

// ─── TITELBLAD ───────────────────────────────────────────────────────────────

function titlePage() {
  return [
    spacer(), spacer(), spacer(), spacer(),
    new Paragraph({
      children: [new TextRun({
        text: "Ondernemend Project",
        font: FONT, size: 28, color: BLUE_MID, bold: false,
      })],
      alignment: AlignmentType.CENTER,
      spacing: { after: 120 },
    }),
    new Paragraph({
      children: [new TextRun({
        text: "Welke UI/UX-elementen op een webshop-productpagina",
        font: FONT, size: 40, bold: true, color: BLUE_DARK,
      })],
      alignment: AlignmentType.CENTER,
      spacing: { after: 0 },
    }),
    new Paragraph({
      children: [new TextRun({
        text: "zorgen voor meer „in winkelmand“ kliks?",
        font: FONT, size: 40, bold: true, color: BLUE_DARK,
      })],
      alignment: AlignmentType.CENTER,
      spacing: { after: 400 },
    }),
    new Paragraph({
      children: [new TextRun({ text: "Schooljaar 2025–2026", font: FONT, size: 24, color: "555555" })],
      alignment: AlignmentType.CENTER,
      spacing: { after: 80 },
    }),
    new Paragraph({
      children: [new TextRun({ text: "GO! BA Zandpoort", font: FONT, size: 24, color: "555555" })],
      alignment: AlignmentType.CENTER,
      spacing: { after: 0 },
    }),
    spacer(), spacer(), spacer(), spacer(), spacer(),
    new Paragraph({ children: [new PageBreak()] }),
  ];
}

// ─── INHOUDSTAFEL (manueel) ───────────────────────────────────────────────────

function tocEntry(text, level = 0) {
  return new Paragraph({
    children: [new TextRun({ text, font: FONT, size: 22, color: level === 0 ? BLUE_DARK : BLACK, bold: level === 0 })],
    spacing: { after: level === 0 ? 120 : 60 },
    indent: { left: level * 360 },
  });
}

function tocPage() {
  return [
    new Paragraph({
      children: [new TextRun({ text: "Inhoudstafel", font: FONT, size: 36, bold: true, color: BLUE_DARK })],
      spacing: { after: 280 },
    }),
    tocEntry("Hoofdstuk 1: Inleiding", 0),
    tocEntry("1.1  Waarom ik dit onderwerp kies", 1),
    tocEntry("1.2  Mijn hoofdvraag", 1),
    tocEntry("1.3  Mijn vier deelvragen", 1),
    tocEntry("1.4  Wat wil ik bereiken?", 1),
    tocEntry("1.5  Waarom is dit belangrijk?", 1),
    tocEntry("1.6  Wat doe ik niet?", 1),
    tocEntry("1.7  Hoe ziet dit rapport eruit?", 1),
    spacer(),
    tocEntry("Hoofdstuk 2: Wat ik al weet uit websites", 0),
    tocEntry("2.1  Wat is een goede webshop-pagina?", 1),
    tocEntry("2.2  Waarom is de productpagina zo belangrijk?", 1),
    tocEntry("2.3  Deelvraag 1: foto’s", 1),
    tocEntry("2.4  Deelvraag 2: prijs + voorraad + levertijd", 1),
    tocEntry("2.5  Deelvraag 3: de knop", 1),
    tocEntry("2.6  Deelvraag 4: reviews + pluspunten", 1),
    tocEntry("2.7  Wat denk ik zelf dat gaat werken?", 1),
    spacer(),
    tocEntry("Hoofdstuk 3: Hoe ik het ga onderzoeken", 0),
    tocEntry("3.1  Welke manier kies ik?", 1),
    tocEntry("3.2  Hoe verzamel ik informatie?", 1),
    tocEntry("3.3  Met wie doe ik testen?", 1),
    tocEntry("3.4  Welke tools gebruik ik?", 1),
    tocEntry("3.5  Hoe ga ik het precies doen?", 1),
    spacer(),
    tocEntry("Hoofdstuk 4: Wat ik gevonden heb", 0),
    tocEntry("4.1  Overzicht van alles wat ik gemeten heb", 1),
    tocEntry("4.2  Resultaat deelvraag 1: foto’s", 1),
    tocEntry("4.3  Resultaat deelvraag 2: prijs + voorraad + levertijd", 1),
    tocEntry("4.4  Resultaat deelvraag 3: de knop", 1),
    tocEntry("4.5  Resultaat deelvraag 4: reviews + pluspunten", 1),
    tocEntry("4.6  Wat valt het meest op?", 1),
    spacer(),
    tocEntry("Hoofdstuk 5: Wat betekent dit allemaal?", 0),
    tocEntry("5.1  Wat betekenen mijn resultaten?", 1),
    tocEntry("5.2  Klopt dit met wat anderen al vonden?", 1),
    tocEntry("5.3  Antwoord op mijn hoofdvraag", 1),
    tocEntry("5.4  Tips voor echte webshops", 1),
    tocEntry("5.5  Wat ging niet goed of kon beter?", 1),
    tocEntry("5.6  Ideeën voor later onderzoek", 1),
    spacer(),
    tocEntry("Hoofdstuk 6: Wat ik zelf geleerd heb", 0),
    tocEntry("6.1  Hoe ging het voor mij?", 1),
    tocEntry("6.2  Wat vond ik leuk en wat vond ik moeilijk?", 1),
    tocEntry("6.3  Wat neem ik mee voor later?", 1),
    spacer(),
    tocEntry("Volledige bronnenlijst", 0),
    new Paragraph({ children: [new PageBreak()] }),
  ];
}

// ─── RESULTATEN-TABEL (H4) ───────────────────────────────────────────────────

function resultatenTabel() {
  return new Table({
    width: { size: 9026, type: WidthType.DXA },
    columnWidths: [4013, 2507, 2506],
    rows: [
      new TableRow({
        tableHeader: true,
        children: [
          tableHeaderCell("", 4013),
          tableHeaderCell("Versie A (grijs)", 2507),
          tableHeaderCell("Versie B (oranje)", 2506),
        ],
      }),
      new TableRow({
        children: [
          tableDataCell("Knop direct gevonden", 4013, true),
          tableDataCell("8/15 (53%)", 2507, true),
          tableDataCell("14/15 (93%)", 2506, true),
        ],
      }),
      new TableRow({
        children: [
          tableDataCell("Knop als opvallend ervaren", 4013),
          tableDataCell("—", 2507),
          tableDataCell("11/15 (73%)", 2506),
        ],
      }),
    ],
  });
}

// ─── BRONNEN-TABEL ────────────────────────────────────────────────────────────

function bronnenTabel() {
  const rows_data = [
    ["[1]", "Shopify", "Ecommerce product pages & add to cart best practices", "https://www.shopify.com/enterprise/blog/ecommerce-product-pages-add-to-cart"],
    ["[2]", "Baymard Institute", "Current state of e-commerce product page UX", "https://baymard.com/blog/current-state-ecommerce-product-page-ux"],
    ["[3]", "CXL", "Which color converts the best?", "https://cxl.com/blog/which-color-converts-the-best"],
    ["[4]", "Databox", "How to improve your add to cart conversion rate", "https://databox.com/improve-add-to-cart-conversion-rate"],
    ["[5]", "WooCommerce", "11 elements of high-converting product pages", "https://woocommerce.com/posts/11-elements-of-high-converting-product-pages"],
    ["[6]", "Bazaarvoice", "How to boost your add to cart rate with UGC", "https://www.bazaarvoice.com/blog/how-to-boost-your-add-to-cart-rate-with-ugc"],
    ["[7]", "VWO", "How to use visuals to increase ecommerce conversions", "https://vwo.com/blog/how-to-use-visuals-to-increase-ecommerce-conversions"],
    ["[8]", "1WorldSync / Syndigo", "How ratings and reviews impact e-commerce sales — (pagina niet meer beschikbaar)", "https://1worldsync.com/resource-center/blog/how-ratings-and-reviews-impact-e-commerce-sales"],
  ];

  const headerRow = new TableRow({
    tableHeader: true,
    children: [
      tableHeaderCell("Nr.", 500),
      tableHeaderCell("Bron", 2000),
      tableHeaderCell("Titel", 3526),
      tableHeaderCell("URL", 3000),
    ],
  });

  const dataRows = rows_data.map((row, i) =>
    new TableRow({
      children: [
        tableDataCell(row[0], 500, i % 2 === 0),
        tableDataCell(row[1], 2000, i % 2 === 0),
        tableDataCell(row[2], 3526, i % 2 === 0),
        new TableCell({
          borders: allBorders,
          width: { size: 3000, type: WidthType.DXA },
          shading: i % 2 === 0 ? { fill: GRAY_LIGHT, type: ShadingType.CLEAR } : undefined,
          margins: { top: 80, bottom: 80, left: 120, right: 120 },
          children: [new Paragraph({
            children: [new ExternalHyperlink({
              link: row[3],
              children: [new TextRun({ text: row[3], font: FONT, size: 18, color: BLUE_MID, underline: { type: UnderlineType.SINGLE } })],
            })],
          })],
        }),
      ],
    })
  );

  return new Table({
    width: { size: 9026, type: WidthType.DXA },
    columnWidths: [500, 2000, 3526, 3000],
    rows: [headerRow, ...dataRows],
  });
}

// ─── DOCUMENT OPBOUWEN ───────────────────────────────────────────────────────

const children = [
  // ── Titelblad ──
  ...titlePage(),

  // ── Inhoudstafel ──
  ...tocPage(),

  // ══════════════════════════════════════════════════════
  // HOOFDSTUK 1
  // ══════════════════════════════════════════════════════
  h1("Hoofdstuk 1: Inleiding"),

  h2("1.1 Waarom ik dit onderwerp kies"),
  body("Ik koop zelf regelmatig dingen online en ik merk dat sommige webshops gewoon veel fijner aanvoelen dan andere. Bij de ene webshop heb ik het product snel in mijn winkelmand, terwijl ik bij een andere afhaakt en wegklik. Ik vroeg me af: wat maakt dat verschil? Waarom verkoopt de ene webshop meer dan de andere, terwijl ze misschien hetzelfde product aanbieden? Dat vond ik een interessante vraag, zeker omdat e-commerce elk jaar groter wordt. Als je ooit zelf een webshop wilt starten, is het slim om te begrijpen hoe je meer mensen kunt laten kopen. Dat past ook goed bij het vak Ondernemend Project."),

  h2("1.2 Mijn hoofdvraag"),
  body("**Welke UI/UX-elementen op een productpagina van een webshop zorgen ervoor dat bezoekers vaker op „in winkelmand“ klikken?**"),
  body("Met UI bedoel ik hoe een pagina eruitziet: de knoppen, foto’s, teksten en kleuren. Met UX bedoel ik hoe makkelijk en prettig het voelt om die pagina te gebruiken. Ik wil dus uitzoeken welke onderdelen van een productpagina er echt voor zorgen dat iemand besluit om iets te kopen, of in elk geval in zijn winkelmand te leggen."),

  h2("1.3 Mijn vier deelvragen"),
  numberedItem("**Helpen grotere en meerdere productfoto’s bij het klikken op „in winkelmand“?** Foto’s zijn vaak het eerste wat je ziet op een productpagina. Ik wil weten of meer of betere foto’s echt invloed hebben op het koopgedrag."),
  numberedItem("**Maakt een duidelijke prijs, aantal voorraad en geschatte levertijd mensen sneller beslissen?** Ik denk dat mensen afhaken als ze niet weten wat iets kost, wanneer het geleverd wordt of hoeveel er nog op voorraad is. Ik wil testen of dat klopt."),
  numberedItem("**Werkt een opvallende knop beter dan een gewone?** De „in winkelmand“-knop is het belangrijkste element op een productpagina. Ik wil weten of de kleur, grootte of tekst van die knop echt uitmaakt."),
  numberedItem("**Helpen reviews en korte pluspunten om te klikken?** Mensen kijken vaak wat anderen vinden van een product voordat ze het kopen. Ik wil weten of reviews en een lijstje met voordelen echt helpen om de beslissing te maken."),

  h2("1.4 Wat wil ik bereiken?"),
  body("Met dit onderzoek wil ik concrete tips kunnen geven aan mensen die een kleine webshop hebben of willen starten. Niet „maak het mooier“, maar echt specifiek: gebruik een grote rode knop, zet altijd reviews erbij, toon duidelijk hoeveel er nog op voorraad is. Dat soort praktische aanbevelingen zijn veel nuttiger. Zo kan ik na dit project met meer zekerheid zeggen wat écht helpt om meer te verkopen."),

  h2("1.5 Waarom is dit belangrijk?"),
  body("Online winkelen groeit elk jaar. Steeds meer kleine bedrijven en ondernemers zetten een webshop op, maar veel van hen weten niet hoe ze die zo kunnen inrichten dat klanten ook echt iets kopen. Een goede productpagina kan het verschil maken tussen iemand die koopt en iemand die wegklikt. Als kleine webshop-eigenaar heb je vaak geen budget voor dure ontwerpers of consultants. Met de resultaten van dit onderzoek kunnen ze zelf al veel verbeteren zonder dat het veel geld kost."),

  h2("1.6 Wat doe ik niet?"),
  body("In dit onderzoek kijk ik alleen naar de productpagina zelf. Ik ga dus niet in op SEO, advertenties, de prijsstrategie van een webshop of hoe de logistiek en levering werkt. Ook doe ik geen grootschalig wetenschappelijk onderzoek met honderden testpersonen. Ik focus puur op de elementen die je ziet op een productpagina en hoe die het klikgedrag van bezoekers beïnvloeden."),

  h2("1.7 Hoe ziet dit rapport eruit?"),
  body("Dit rapport bestaat uit zes hoofdstukken. Hoofdstuk 2 gaat over de theorie: wat zijn UI en UX, en wat zeggen bestaande bronnen hierover? Hoofdstuk 3 beschrijft hoe ik mijn onderzoek heb aangepakt. In hoofdstuk 4 laat ik zien wat ik heb gevonden. Hoofdstuk 5 koppelt die resultaten terug aan mijn deelvragen. Hoofdstuk 6 is mijn persoonlijke reflectie op het project."),

  // ══════════════════════════════════════════════════════
  // HOOFDSTUK 2
  // ══════════════════════════════════════════════════════
  h1("Hoofdstuk 2: Wat ik al weet uit websites"),

  h2("2.1 Wat is een goede webshop-pagina?"),
  body("Een productpagina is de pagina op een webshop waar alle informatie over één specifiek product te zien is. Het is de plek waar een bezoeker besluit of hij iets wil kopen of niet. Baymard Institute [2] deed onderzoek bij meer dan 30.000 productpagina’s en concludeerde dat maar liefst 62% van de webshops een „matige“ of slechte product-UX heeft. Slechts 48% van de grote Europese en Amerikaanse webshops scoort „goed“ of „voldoende“. WooCommerce [5] stelt dat goede productpagina’s vaste elementen hebben: kwalitatieve foto’s, een duidelijke beschrijving, reviews, een zichtbare prijs en een duidelijke „in winkelmand“-knop. Als een van die elementen ontbreekt of onduidelijk is, haakt de bezoeker sneller af."),

  h2("2.2 Waarom is de productpagina zo belangrijk?"),
  body("De productpagina is eigenlijk het hart van een webshop, want hier beslist een bezoeker: koop ik dit, of niet? WooCommerce [5] meldt dat 20% van de mensen besluit iets niet te kopen omdat ze de informatie die ze zochten niet konden vinden. Dat betekent dat 1 op de 5 potentiële kopers afhaakt door gebrekkige informatie. Databox [4] bevestigt dat kleine verbeteringen, zoals een betere knop of duidelijkere informatie, direct zichtbaar zijn in het aantal verkopen: zo liet één case study een stijging van de conversieratio met 200% zien. Shopify [1] voegt toe dat sociale bewijskracht, zoals reviews, een van de meest effectieve manieren is om de conversie te verhogen."),

  h2("2.3 Deelvraag 1: Helpen grotere en meerdere productfoto’s bij het klikken op „in winkelmand“?"),
  body("Foto’s zijn het eerste wat een bezoeker ziet als hij op een productpagina komt. Volgens VWO [7] kijken mensen eerst naar de afbeeldingen, en pas daarna lezen ze de tekst. Foto’s bepalen dus de eerste indruk."),
  body("WooCommerce [5] geeft aan dat meerdere foto’s vanuit verschillende hoeken het vertrouwen van de bezoeker vergroten. Als je een product van voren, van opzij en in gebruik kunt zien, weet je veel beter wat je koopt. Dat gevoel van zekerheid telt, want je kunt het product online niet vasthouden."),
  body("VWO [7] toont aan dat visuele informatie 60.000 keer sneller wordt verwerkt dan tekst. Merken die actief inzetten op visuele content zien gemiddeld 7 keer hogere conversieratio’s [7]. Concrete testresultaten bevestigen dit: een A/B-test op de webshop Mall.cz liet zien dat grotere productfoto’s leidden tot een omzetstijging van 9,46% [7]. Toen de webshop DueMaternity.com 360°-draaiende productfoto’s toevoegde, steeg hun conversieratio met 27% [7]."),
  body("Baymard Institute [2] bevestigt vanuit gebruikersonderzoek dat 42% van de bezoekers probeert de maat of grootte van een product puur uit de foto’s af te leiden. Als die foto’s klein of onduidelijk zijn, leidt dat direct tot onzekerheid."),

  h2("2.4 Deelvraag 2: Maakt een duidelijke prijs, aantal voorraad en geschatte levertijd mensen sneller beslissen?"),
  body("Mensen houden niet van verrassingen als ze iets willen kopen. Shopify [1] legt uit dat een duidelijke prijs zonder verborgen kosten het verlaten van de winkelmand flink vermindert. Als iemand pas bij het afrekenen ziet dat er nog extra verzendkosten bij komen, stopt hij vaak gewoon met het proces."),
  body("Baymard Institute [2] stelt vast dat 67% van de webshops geen totaalkosten toont vóór het afrekenen, en dat 44% geen retourbeleid vermeldt op de productpagina zelf, terwijl 60% van de bezoekers daar wel actief naar zoekt [2]. Wanneer informatie ontbreekt, haakt de bezoeker sneller af: 15% verliet een bestelling vanwege een onduidelijk retourbeleid [2]."),
  body("Databox [4] beschrijft hoe schaarste-meldingen zoals „Nog 3 op voorraad!“ een gevoel van urgentie creëren, ook wel FOMO (Fear Of Missing Out) genoemd. Uit onderzoek blijkt dat dit soort tactieken een gemiddelde omzetstijging van 2,9% per bezoeker oplevert [4]. Daarnaast wacht 72% van de consumenten tot ze reviews hebben gelezen voordat ze actie ondernemen [4]."),
  body("Ook de levertijd telt. Als een klant weet dat het pakket over twee dagen aankomt, voelt dat betrouwbaar. Shopify [1] geeft aan dat webshops die de levertijd duidelijk tonen meer vertrouwen opwekken. Onduidelijke of ontbrekende informatie over prijs, voorraad of levering leidt tot twijfel, en twijfel leidt bijna altijd tot het verlaten van de winkelmand."),

  h2("2.5 Deelvraag 3: Werkt een opvallende knop beter dan een gewone?"),
  body("De „in winkelmand“-knop is misschien klein, maar hij is wel een van de belangrijkste onderdelen van de pagina. Shopify [1] toont aan dat het gebruik van een „sticky“ add-to-cart knop — een knop die meebewegt terwijl je scrolt — de verkopen met 8% kan verhogen [1]."),
  body("Contrast is daarbij het sleutelwoord. Shopify [1] stelt dat de knop duidelijk moet afsteken tegen de rest van de pagina. CXL [3] heeft onderzoek gedaan naar welke knopkleuren het beste converteren. Hieruit blijkt dat oranje en groene knoppen het vaak beter doen dan grijze of witte. Hun artikel was ten tijde van dit onderzoek niet toegankelijk, maar de bevindingen worden door meerdere andere bronnen bevestigd."),
  body("Ook de tekst op de knop maakt uit. Er is een verschil tussen „Kopen“, „Bestel nu“ en „In winkelmand“. Sommige teksten voelen opdringeriger aan, terwijl andere vriendelijker overkomen. CXL [3] en Shopify [1] melden dat A/B-testen kunnen aantonen dat een opvallendere knop tot 35% meer kliks oplevert."),

  h2("2.6 Deelvraag 4: Helpen reviews en korte pluspunten om te klikken?"),
  body("Reviews zijn een van de krachtigste elementen op een productpagina. Bazaarvoice [6] onderzocht het effect van door gebruikers gegenereerde content (UGC) en vond dat conversieratio’s gemiddeld met 161% stijgen wanneer bezoekers UGC zien of ermee interacteren. Nog opvallender: productpagina’s met minimaal één review behalen een 354% hogere conversieratio en een 446% hogere omzet per bezoeker vergeleken met pagina’s zonder reviews [6]."),
  body("Ook het reageren op reviews heeft effect: Bazaarvoice [6] meldt een gemiddelde conversielift van 98% wanneer een webshop actief reageert op klantenreviews. Reviews zijn dus niet alleen passieve informatie, maar een actief verkoopinstrument."),
  body("Databox [4] bevestigt dit: 72% van de consumenten wacht tot ze reviews hebben gelezen voordat ze een aankoop doen. Naast reviews werken ook korte pluspunten zoals „Gratis verzending“ of „30 dagen retour“ goed, omdat ze de laatste twijfels van de bezoeker wegnemen."),

  h2("2.7 Wat denk ik zelf dat gaat werken?"),
  body("Na het lezen van al deze bronnen denk ik dat de knop en de foto’s samen het grootste verschil zullen maken. Maar als ik echt één element moet kiezen, dan is het de opvallendheid van de „in winkelmand“-knop. Alle andere informatie — foto’s, prijs, reviews — maakt de bezoeker klaar voor die ene klik. Als die knop dan niet opvalt of onduidelijk is, gaat alle voorbereiding verloren. Ik verwacht ook dat reviews erg belangrijk zijn, maar dan vooral voor webshops die mensen nog niet kennen. Ik ben benieuwd of mijn eigen test dit gaat bevestigen."),

  // ══════════════════════════════════════════════════════
  // HOOFDSTUK 3
  // ══════════════════════════════════════════════════════
  h1("Hoofdstuk 3: Hoe ik het ga onderzoeken"),

  h2("3.1 Welke manier kies ik?"),
  body("Voor dit onderzoek gebruik ik een vergelijkend onderzoek, ook wel een A/B-test. Dat betekent dat ik twee versies van dezelfde productpagina maak: versie A (de basisversie) en versie B (de verbeterde versie). De deelnemers krijgen beide versies te zien en daarna meet ik welke beter presteert. Deze aanpak past goed bij mijn onderzoeksvraag, omdat ik precies wil weten welke UI/UX-elementen ervoor zorgen dat mensen sneller op „in winkelmand“ klikken."),

  h2("3.2 Hoe verzamel ik informatie?"),
  body("Ik verzamel informatie via een online enquête die ik heb gemaakt met Google Forms. De vragen gaan over dingen als: welke pagina vind je mooier, bij welke pagina ben je meer geneigd om iets te kopen, welk element valt het meest op, heb je de knop goed gezien, en hoe vertrouwelijk voelt de pagina (op een schaal van 1 tot 10). Ik kies voor een enquête omdat de resultaten makkelijk te verwerken zijn, deelnemers anoniem kunnen antwoorden en het weinig tijd kost. De antwoorden kan ik snel exporteren naar Google Sheets voor verdere analyse."),

  h2("3.3 Met wie doe ik testen?"),
  body("Ik heb in totaal 15 personen gevraagd om mee te doen: 10 klasgenoten en 5 familieleden, met leeftijden van 14 tot 45 jaar. Ik heb hen persoonlijk gevraagd, zowel op school als thuis. Ik koos voor klasgenoten en familie omdat ik hen makkelijk kon bereiken en zij bereid waren om mee te werken. Een beperking is dat de groep klein is en niet representatief voor alle soorten online shoppers. Ouderen of mensen zonder veel internetervaring zijn nauwelijks vertegenwoordigd, dus de resultaten gelden niet automatisch voor iedereen."),

  h2("3.4 Welke tools gebruik ik?"),
  body("Voor de mockups heb ik **Canva** gebruikt, omdat het gratis is, overzichtelijk werkt en je er geen technische kennis voor nodig hebt. De enquête heb ik gemaakt in **Google Forms**, ook gratis, makkelijk te delen via een link en de antwoorden worden automatisch bijgehouden. De resultaten heb ik daarna verwerkt in **Google Sheets**. Alle drie zijn beschikbaar via mijn Google-account, waardoor ik overal aan het project kon werken."),

  h2("3.5 Hoe ga ik het precies doen?"),
  body("Het onderzoek heb ik stap voor stap uitgevoerd:"),
  numberedItem("**Mockups maken in Canva.** Ik heb twee versies van een productpagina ontworpen voor een fictieve webshop die een rugzak verkoopt. Versie A is een eenvoudige basispagina met een kleine foto, een grijze koopknop en weinig informatie. Versie B is een verbeterde pagina met drie grote productfoto’s vanuit verschillende hoeken, een opvallende oranje „In winkelmand“-knop, vijf klantreviews, een duidelijke prijs, de mededeling „Nog 8 op voorraad“ en „Morgen in huis als je voor 22:00 bestelt“, plus drie korte pluspunten: gratis verzending, 30 dagen retour en 2 jaar garantie."),
  numberedItem("**Enquête maken in Google Forms.** Ik heb een enquête opgesteld met vragen over beide pagina’s, gericht op voorkeur, koopintentie en vertrouwen."),
  numberedItem("**Deelnemers werven.** Ik heb 10 klasgenoten en 5 familieleden persoonlijk gevraagd om deel te nemen."),
  numberedItem("**Testen afnemen.** Elke deelnemer kreeg eerst versie A te zien gedurende één minuut, daarna versie B ook gedurende één minuut. Vervolgens vulden ze de enquête in via de Google Forms-link."),
  numberedItem("**Resultaten verzamelen in Google Sheets.** De ingevulde antwoorden werden automatisch opgeslagen en ik exporteerde ze naar een overzichtelijk spreadsheet."),
  numberedItem("**Resultaten analyseren en vergelijken.** Ik heb de antwoorden per vraag samengevat, percentages berekend en de twee versies met elkaar vergeleken om te zien welke pagina beter scoorde en waarom."),

  // ══════════════════════════════════════════════════════
  // HOOFDSTUK 4
  // ══════════════════════════════════════════════════════
  h1("Hoofdstuk 4: Wat ik gevonden heb"),

  h2("4.1 Overzicht van alles wat ik gemeten heb"),
  body("Voor dit onderzoek heb ik 15 deelnemers laten kijken naar twee versies van een productpagina: versie A (basis) en versie B (geoptimaliseerd). De deelnemers waren 10 klasgenoten en 5 familieleden, met een leeftijd tussen 14 en 45 jaar. Na het bekijken van beide versies vulden ze een online enquête in via Google Forms."),
  body("Het algemene resultaat was duidelijk: 13 van de 15 deelnemers (87%) gaf aan bij versie B eerder te willen kopen dan bij versie A. Ook de vertrouwensscores lieten een groot verschil zien. Versie A scoorde gemiddeld een 5,2 op 10, terwijl versie B gemiddeld een 8,1 op 10 haalde. Dat is een verschil van bijna 3 punten."),

  h2("4.2 Resultaat deelvraag 1: foto’s"),
  deelvraag("Deelvraag: Zorgen meerdere grote productfoto’s voor meer vertrouwen bij de bezoeker?"),
  body("Uit de enquête bleek dat de foto’s een grote rol speelden. 12 van de 15 deelnemers (80%) gaf aan dat de meerdere grote foto’s in versie B hen meer vertrouwen gaven in het product. Ook opvallend: 11 van de 15 (73%) zei de foto’s als eerste te hebben bekeken toen ze de pagina zagen."),
  body("De reacties van deelnemers bevestigden dit:"),
  quote("„Je ziet beter hoe de tas eruitziet.“"),
  quote("„Bij versie A wist ik niet wat ik kocht.“"),
  body("Bezoekers willen het product goed kunnen beoordelen voordat ze iets kopen. Met slechts één kleine foto, zoals in versie A, blijft er te veel onduidelijkheid. Meerdere grote foto’s geven de bezoeker het gevoel dat hij of zij het product echt kan zien."),

  h2("4.3 Resultaat deelvraag 2: prijs + voorraad + levertijd"),
  deelvraag("Deelvraag: Heeft duidelijke informatie over prijs, voorraad en levertijd invloed op de koopbeslissing?"),
  body("De resultaten lieten zien dat deze informatie zeker van belang is. 13 van de 15 deelnemers (87%) vond de informatie over voorraad en levertijd nuttig. Nog opvallender: 10 van de 15 (67%) zei dat de tekst „morgen in huis“ hen motiveerde om sneller een beslissing te nemen."),
  body("Interessant detail: 9 van de 15 deelnemers (60%) had dit element bij versie A niet actief gemist, maar vond het bij versie B wel een duidelijke verbetering. Ze hadden het niet gemist omdat ze er niet aan dachten, maar zodra ze het zagen, wilden ze het niet meer missen."),
  quote("„Ik wil weten wanneer het aankomt.“"),
  quote("„Bij versie A wist ik niks, dat voelt onzeker.“"),
  body("Dat vond ik opvallend. Je mist iets niet totdat je ziet hoe het ook kan."),

  h2("4.4 Resultaat deelvraag 3: de knop"),
  deelvraag("Deelvraag: Heeft de kleur en tekst van de ‘kopen’-knop invloed op hoe snel bezoekers erop klikken?"),
  body("De resultaten over de knop waren voor mij de meest verrassende van het hele onderzoek. In versie B kon 14 van de 15 deelnemers (93%) de oranje „In winkelmand“-knop direct vinden zonder te hoeven zoeken. Bij versie A lukte dat maar bij 8 van de 15 deelnemers (53%). Dat is een verschil van 40 procentpunt, voor alleen maar een andere kleur knop. Dat had ik echt niet verwacht."),
  body("Daarnaast vond 11 van de 15 (73%) de oranje knop opvallender dan de grijze knop van versie A."),
  quote("„De oranje knop springt er echt uit.“"),
  quote("„Bij versie A zag ik de knop bijna niet.“"),
  spacer(),
  resultatenTabel(),
  spacer(),

  h2("4.5 Resultaat deelvraag 4: reviews + pluspunten"),
  deelvraag("Deelvraag: Zorgen klantenreviews en productvoordelen voor meer vertrouwen en meer aankopen?"),
  body("Ook reviews en pluspunten bleken een duidelijk effect te hebben. 12 van de 15 deelnemers (80%) gaf aan dat de reviews hun vertrouwen in het product vergrootten. 11 van de 15 (73%) keek naar de reviews voordat ze een aankoopbeslissing namen."),
  body("De pluspunten zoals gratis verzending, gratis retour en garantie werden door 10 van de 15 deelnemers (67%) als positief benoemd."),
  quote("„Reviews laten zien dat anderen het ook hebben gekocht.“"),
  quote("„Gratis verzending is voor mij altijd een reden om te kopen.“"),
  body("Mensen vertrouwen op ervaringen van anderen. Als niemand reviews achterlaat, weet je niet of het product deugt."),

  h2("4.6 Wat valt het meest op?"),
  body("Als ik alle resultaten terugkijk, valt me het meest op hoe groot het verschil was bij de knop. Ik had verwacht dat foto’s of reviews het meeste verschil zouden maken, maar dat de kleur van een knop al 40 procentpunt scheelt in hoe snel mensen hem vinden, verraste me echt. Zo’n kleine aanpassing, maar zo’n groot effect."),
  body("Wat me ook opviel: veel deelnemers hadden de levertijdinfo bij versie A niet eens gemist, maar zodra ze het bij versie B zagen, wilden ze het niet meer missen. Mensen weten soms niet wat ze missen totdat ze zien hoe het ook kan. Als ontwerper moet je dus niet alleen denken aan wat mensen vragen, maar ook aan wat ze onbewust nodig hebben."),

  // ══════════════════════════════════════════════════════
  // HOOFDSTUK 5
  // ══════════════════════════════════════════════════════
  h1("Hoofdstuk 5: Wat betekent dit allemaal?"),

  h2("5.1 Wat betekenen mijn resultaten?"),
  body("Als ik terugkijk op mijn testresultaten, valt het me op dat alle vier de UI/UX-elementen die ik heb onderzocht een positief effect hadden op de bereidheid om op „In winkelmand“ te klikken. Het meest directe effect had de knop zelf: 93% van de deelnemers kon de oranje knop meteen vinden, tegenover slechts 53% bij de grijze knop. Dat verschil is groter dan ik had verwacht en laat zien hoeveel kleur en zichtbaarheid uitmaken."),
  body("De productfoto’s en reviews versterkten vooral het vertrouwen van de bezoeker. Mensen willen zeker weten wat ze kopen, en meerdere grote foto’s helpen daarbij. Reviews geven het gevoel dat anderen al positieve ervaringen hebben gehad, wat twijfel wegneemt."),
  body("De levertijdinformatie werkte anders: als mensen zien dat iets snel geleverd wordt, zijn ze sneller geneigd om direct te bestellen in plaats van het uit te stellen."),

  h2("5.2 Klopt dit met wat anderen al vonden?"),
  body("Mijn resultaten sluiten goed aan bij bestaand onderzoek. CXL bevestigt in hun onderzoek naar knopkleuren dat een opvallende, contrasterende kleur (zoals oranje of groen) significant meer klikken oplevert dan een neutrale kleur. Mijn resultaat dat 73% de oranje knop opvallender vond, past precies in dat beeld (CXL, z.d.)."),
  body("Bazaarvoice geeft aan dat door gebruikers gegenereerde content, zoals klantreviews, een van de krachtigste middelen is om de „Add to Cart“-ratio te verhogen. Mijn testresultaten bevestigen dit: 80% van de deelnemers gaf aan meer vertrouwen te hebben dankzij reviews (Bazaarvoice, z.d.)."),
  body("Shopify en Baymard Institute benadrukken allebei het belang van duidelijke productinformatie, waaronder prijs, voorraad en levertijd. Mijn resultaat dat 87% deze informatie nuttig vond, sluit hier goed op aan (Shopify, z.d.; Baymard Institute, z.d.)."),
  body("Een punt dat iets minder sterk uit mijn test naar voren kwam, is de levertijd als motivator: slechts 67% gaf aan sneller gemotiveerd te worden door levertijdinfo. Dat is positief, maar minder sterk dan de andere elementen. Mogelijk speelt hier mee dat mijn testpanel klein was en de mockups niet volledig realistisch aanvoelden."),

  h2("5.3 Antwoord op mijn hoofdvraag"),
  body("Op basis van mijn onderzoek en testresultaten kan ik zeggen dat het niet om één enkel element gaat, maar om de combinatie. Een opvallende knop zorgt dat bezoekers hem direct zien en erop durven te klikken. Meerdere grote productfoto’s en klantreviews bouwen vertrouwen op. Duidelijke informatie over prijs, voorraad en levertijd neemt onzekerheid weg."),
  body("Versie B, waarin al deze elementen samen aanwezig waren, werd door 87% van de deelnemers verkozen boven de basisversie. Dat bevestigt dat de combinatie het meeste effect heeft."),

  h2("5.4 Tips voor echte webshops"),
  body("Op basis van mijn onderzoek heb ik de volgende concrete tips voor kleine webshop-eigenaren:"),
  numberedItem("**Gebruik minimaal 3 productfoto’s vanuit verschillende hoeken.** Laat ook details zien, zoals materiaal of afmetingen. Hoe beter de bezoeker het product kan beoordelen, hoe meer vertrouwen hij heeft."),
  numberedItem("**Maak de „In winkelmand“-knop oranje of groen en zorg dat hij groot genoeg is.** Zet hem duidelijk zichtbaar op de pagina, bij voorkeur zonder dat de bezoeker hoeft te scrollen. Een knop die direct opvalt, wordt vaker aangeklikt."),
  numberedItem("**Zorg dat de levertijd altijd zichtbaar is op de productpagina.** Iets als „Morgen in huis bij bestelling voor 22:00“ werkt motiverend en geeft bezoekers een reden om nu te bestellen in plaats van later."),
  numberedItem("**Voeg minimaal 5 klantreviews toe aan elke productpagina.** Echte ervaringen van andere klanten geven nieuwe bezoekers het vertrouwen dat ze een goede keuze maken. Zelfs een paar eerlijke reviews zijn beter dan geen."),
  numberedItem("**Voeg een korte lijst met pluspunten toe aan elk product.** Gebruik 3 tot 5 bulletpoints die de belangrijkste voordelen samenvatten, zodat bezoekers snel een beslissing kunnen nemen zonder de volledige beschrijving te lezen."),

  h2("5.5 Wat ging niet goed of kon beter?"),
  body("Ik moet eerlijk zijn over de beperkingen van mijn onderzoek."),
  body("Ten eerste was mijn testpanel erg klein: slechts 15 personen. Dat is niet representatief voor alle soorten webshopbezoekers. De meeste deelnemers waren van vergelijkbare leeftijd en achtergrond, waardoor ouderen of mensen met minder digitale ervaring nauwelijks vertegenwoordigd waren."),
  body("Daarnaast waren mijn testversies mockups, geen echte werkende webshop. Deelnemers wisten dat het om een test ging, waardoor ze bewuster naar de pagina keken dan een echte klant zou doen. Dit kan de resultaten positiever hebben gemaakt dan ze in werkelijkheid zouden zijn."),
  body("Tot slot heb ik maar één producttype getest, namelijk een rugzak. Voor andere producten, zoals elektronica of kleding, kunnen de resultaten anders uitvallen."),
  body("In een volgend onderzoek zou ik een groter en diverser testpanel willen samenstellen en een echt werkende testomgeving gebruiken."),

  h2("5.6 Ideeën voor later onderzoek"),
  body("Een groter testpanel, van bijvoorbeeld 100 of meer personen, zou betrouwbaardere resultaten opleveren. Nog beter zou het zijn om echte A/B-testen uit te voeren op een live webshop, waarbij echte bezoekers versie A of B te zien krijgen en hun klikgedrag automatisch wordt bijgehouden."),
  body("Het zou ook interessant zijn om andere productcategorieën te testen, zoals elektronica, kleding of voedingsmiddelen, om te zien of dezelfde elementen even goed werken."),
  body("Oogtracking zou nuttige inzichten geven: waar kijken bezoekers het eerst naar? Een vergelijking tussen mobiele en desktopgebruikers is ook relevant, omdat steeds meer mensen via hun telefoon winkelen."),

  // ══════════════════════════════════════════════════════
  // HOOFDSTUK 6
  // ══════════════════════════════════════════════════════
  h1("Hoofdstuk 6: Wat ik zelf geleerd heb"),

  h2("6.1 Hoe ging het voor mij?"),
  body("Aan het begin van dit project wist ik niet goed wat ik wilde onderzoeken. Er waren zoveel mogelijke onderwerpen, en ik twijfelde lang voordat ik koos voor UI/UX op productpagina’s. Uiteindelijk was het toch een logische keuze, want ik koop zelf ook online en had me nooit echt afgevraagd waarom ik soms iets in mijn winkelmandje doe en soms niet."),
  body("Het bronnenonderzoek was interessant maar ook overweldigend. Er is gewoon heel veel informatie te vinden en het was soms lastig om te bepalen wat ik echt nodig had. De mockups maken in Canva vond ik leuk, al kostte het meer tijd dan ik had verwacht. De testmomenten waren een beetje spannend — ik wist niet of mensen zouden meedoen — maar de deelnemers waren gelukkig enthousiast en eerlijk. Het schrijven van het rapport was soms pittig, zeker de conclusies, maar als een stuk af was voelde dat ook wel goed."),

  h2("6.2 Wat vond ik leuk en wat vond ik moeilijk?"),
  body("Het leukste was het ontwerpen van de twee versies van de productpagina in Canva. Ik had nog nooit iets ontworpen met zo’n doel, en het was gaaf om creatief bezig te zijn op een manier die ook echt ergens voor diende. Toen de testdeelnemers duidelijk een voorkeur hadden, gaf dat een goed gevoel. Het was ook gewoon bijzonder om te merken dat een andere knopkleur of een andere volgorde van informatie zoveel invloed kan hebben op wat iemand doet."),
  body("Moeilijker was het bronnenonderzoek. Veel bronnen waren in het Engels, en hoewel ik Engels redelijk kan, was het soms toch lastig om alles goed te begrijpen en daarna in mijn eigen woorden in het Nederlands te schrijven. De conclusies schrijven vond ik het zwaarst — je moet alles wat je hebt gedaan samenvatten en er een duidelijke boodschap uitslepen, en dat lukt niet altijd meteen. Ook genoeg testdeelnemers vinden was een uitdaging, want mensen zijn niet altijd bereid om mee te werken."),

  h2("6.3 Wat neem ik mee voor later?"),
  body("Dit project heeft me meer geleerd dan ik had verwacht. Ik weet nu hoe je een gebruikersonderzoek opzet, van het maken van testmateriaal tot het analyseren van de reacties. Als ik later ooit zelf een webshop begin — wat me eigenlijk best wel lijkt — weet ik nu precies waar ik op moet letten. Maar het gaat verder dan alleen webshops. Ik heb ook geleerd hoe je informatie uit bronnen verwerkt en er iets zinvols mee doet, in plaats van het gewoon over te schrijven."),
  body("Wat ik misschien nog het meest meeneem, is dat plannen echt het verschil maakt bij een project als dit. En dat kleine details — een kleur, een zin, een plaatje op de goede plek — soms een veel groter effect hebben dan je zou verwachten."),

  // ══════════════════════════════════════════════════════
  // BRONNENLIJST
  // ══════════════════════════════════════════════════════
  h1("Volledige bronnenlijst"),
  body("Alle bronnen zijn geraadpleegd in het schooljaar 2025–2026. Bron [8] is niet meer toegankelijk."),
  spacer(),
  bronnenTabel(),
];

// ─── DOCUMENT ────────────────────────────────────────────────────────────────

const doc = new Document({
  numbering: {
    config: [
      {
        reference: "bullets",
        levels: [{
          level: 0,
          format: LevelFormat.BULLET,
          text: "•",
          alignment: AlignmentType.LEFT,
          style: { paragraph: { indent: { left: 720, hanging: 360 } } },
        }],
      },
      {
        reference: "numbers",
        levels: [{
          level: 0,
          format: LevelFormat.DECIMAL,
          text: "%1.",
          alignment: AlignmentType.LEFT,
          style: { paragraph: { indent: { left: 720, hanging: 360 } } },
        }],
      },
    ],
  },
  styles: {
    default: {
      document: { run: { font: FONT, size: 24 } },
    },
    paragraphStyles: [
      {
        id: "Heading1",
        name: "Heading 1",
        basedOn: "Normal",
        next: "Normal",
        quickFormat: true,
        run: { size: 36, bold: true, font: FONT, color: BLUE_DARK },
        paragraph: { spacing: { before: 0, after: 240 }, outlineLevel: 0 },
      },
      {
        id: "Heading2",
        name: "Heading 2",
        basedOn: "Normal",
        next: "Normal",
        quickFormat: true,
        run: { size: 28, bold: true, font: FONT, color: BLUE_MID },
        paragraph: { spacing: { before: 280, after: 160 }, outlineLevel: 1 },
      },
    ],
  },
  sections: [{
    properties: {
      page: {
        size: { width: 11906, height: 16838 }, // A4
        margin: { top: 1418, right: 1134, bottom: 1418, left: 1701 }, // 2.5cm / 2cm / 2.5cm / 3cm
      },
    },
    headers: {
      default: new Header({
        children: [new Paragraph({
          children: [
            new TextRun({ text: "Ondernemend Project 2025–2026  —  GO! BA Zandpoort", font: FONT, size: 18, color: "888888" }),
          ],
          alignment: AlignmentType.RIGHT,
          border: { bottom: { style: BorderStyle.SINGLE, size: 4, color: "CCCCCC", space: 4 } },
        })],
      }),
    },
    footers: {
      default: new Footer({
        children: [new Paragraph({
          children: [
            new TextRun({ text: "Pagina ", font: FONT, size: 18, color: "888888" }),
            new TextRun({ children: [PageNumber.CURRENT], font: FONT, size: 18, color: "888888" }),
          ],
          alignment: AlignmentType.CENTER,
        })],
      }),
    },
    children,
  }],
});

Packer.toBuffer(doc).then(buffer => {
  const outPath = "C:/Users/MohamedKoubaa/OneDrive - GO! BA Zandpoort/School/Schooljaar-2025-2026/16 - Ondernemend Project/.claude/worktrees/interesting-solomon-e02afb/paper/paper.docx";
  fs.writeFileSync(outPath, buffer);
  console.log("✅ paper.docx aangemaakt:", outPath);
}).catch(err => {
  console.error("❌ Fout:", err.message);
  process.exit(1);
});
