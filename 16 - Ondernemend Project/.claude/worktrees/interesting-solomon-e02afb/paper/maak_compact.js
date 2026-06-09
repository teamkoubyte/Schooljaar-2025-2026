const {
  Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell,
  Header, Footer, AlignmentType, HeadingLevel, BorderStyle, WidthType,
  ShadingType, PageNumber, PageBreak, LevelFormat, ExternalHyperlink, UnderlineType
} = require('C:/Users/MohamedKoubaa/AppData/Roaming/npm/node_modules/docx');
const fs = require('fs');

const FONT = "Calibri";
const BLUE_DARK = "1F3864";
const BLUE_MID  = "2E5FAC";
const BLACK = "000000";
const GRAY_LIGHT = "F2F2F2";
const GRAY_MED   = "BFBFBF";

// ── Helpers ──────────────────────────────────────────────────────────────────

function runs(text, base = {}) {
  const parts = [];
  const re = /\*\*(.+?)\*\*/g;
  let last = 0, m;
  while ((m = re.exec(text)) !== null) {
    if (m.index > last) parts.push(new TextRun({ text: text.slice(last, m.index), font: FONT, size: 22, ...base }));
    parts.push(new TextRun({ text: m[1], font: FONT, size: 22, bold: true, ...base }));
    last = m.index + m[0].length;
  }
  if (last < text.length) parts.push(new TextRun({ text: text.slice(last), font: FONT, size: 22, ...base }));
  return parts;
}

// Body alinea — compact spacing
function p(text, opts = {}) {
  return new Paragraph({
    children: runs(text, opts.color ? { color: opts.color } : {}),
    spacing: { after: 100, line: 264 },
    alignment: AlignmentType.JUSTIFIED,
  });
}

function spacer() { return new Paragraph({ children: [new TextRun("")], spacing: { after: 60 } }); }

function h1(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_1,
    pageBreakBefore: true,
    children: [new TextRun({ text, font: FONT, size: 32, bold: true, color: BLUE_DARK })],
    spacing: { before: 0, after: 180 },
  });
}

function h2(text) {
  return new Paragraph({
    heading: HeadingLevel.HEADING_2,
    children: [new TextRun({ text, font: FONT, size: 26, bold: true, color: BLUE_MID })],
    spacing: { before: 200, after: 100 },
    border: { bottom: { style: BorderStyle.SINGLE, size: 4, color: BLUE_MID, space: 3 } },
  });
}

function quote(text) {
  return new Paragraph({
    children: [new TextRun({ text, font: FONT, size: 20, italics: true, color: "555555" })],
    spacing: { before: 60, after: 60 },
    indent: { left: 560, right: 560 },
    border: { left: { style: BorderStyle.SINGLE, size: 12, color: BLUE_MID, space: 6 } },
  });
}

function dv(text) {
  return new Paragraph({
    children: [new TextRun({ text, font: FONT, size: 21, italics: true, bold: true, color: "333333" })],
    spacing: { before: 80, after: 80 },
    indent: { left: 300 },
  });
}

function num(text) {
  return new Paragraph({
    numbering: { reference: "numbers", level: 0 },
    children: runs(text),
    spacing: { after: 80, line: 264 },
  });
}

function bul(text) {
  return new Paragraph({
    numbering: { reference: "bullets", level: 0 },
    children: runs(text),
    spacing: { after: 80, line: 264 },
  });
}

// ── Tabel helpers ─────────────────────────────────────────────────────────────
const cb = { style: BorderStyle.SINGLE, size: 4, color: GRAY_MED };
const borders = { top: cb, bottom: cb, left: cb, right: cb };

function th(text, w) {
  return new TableCell({
    borders, width: { size: w, type: WidthType.DXA },
    shading: { fill: BLUE_DARK, type: ShadingType.CLEAR },
    margins: { top: 60, bottom: 60, left: 100, right: 100 },
    children: [new Paragraph({ children: [new TextRun({ text, font: FONT, size: 20, bold: true, color: "FFFFFF" })] })],
  });
}

function td(text, w, shade = false) {
  return new TableCell({
    borders, width: { size: w, type: WidthType.DXA },
    shading: shade ? { fill: GRAY_LIGHT, type: ShadingType.CLEAR } : undefined,
    margins: { top: 60, bottom: 60, left: 100, right: 100 },
    children: [new Paragraph({ children: [new TextRun({ text, font: FONT, size: 20, color: BLACK })] })],
  });
}

function knopTabel() {
  return new Table({
    width: { size: 9026, type: WidthType.DXA },
    columnWidths: [4013, 2507, 2506],
    rows: [
      new TableRow({ tableHeader: true, children: [th("", 4013), th("Versie A (grijs)", 2507), th("Versie B (oranje)", 2506)] }),
      new TableRow({ children: [td("Knop direct gevonden", 4013, true), td("8/15 (53%)", 2507, true), td("14/15 (93%)", 2506, true)] }),
      new TableRow({ children: [td("Knop opvallend ervaren", 4013), td("—", 2507), td("11/15 (73%)", 2506)] }),
    ],
  });
}

function bronnenTabel() {
  const data = [
    ["[1]","Shopify","Ecommerce product pages & add to cart best practices","https://www.shopify.com/enterprise/blog/ecommerce-product-pages-add-to-cart"],
    ["[2]","Baymard Institute","Current state of e-commerce product page UX","https://baymard.com/blog/current-state-ecommerce-product-page-ux"],
    ["[3]","CXL","Which color converts the best?","https://cxl.com/blog/which-color-converts-the-best"],
    ["[4]","Databox","How to improve your add to cart conversion rate","https://databox.com/improve-add-to-cart-conversion-rate"],
    ["[5]","WooCommerce","11 elements of high-converting product pages","https://woocommerce.com/posts/11-elements-of-high-converting-product-pages"],
    ["[6]","Bazaarvoice","How to boost your add to cart rate with UGC","https://www.bazaarvoice.com/blog/how-to-boost-your-add-to-cart-rate-with-ugc"],
    ["[7]","VWO","How to use visuals to increase ecommerce conversions","https://vwo.com/blog/how-to-use-visuals-to-increase-ecommerce-conversions"],
    ["[8]","1WorldSync / Syndigo","How ratings and reviews impact e-commerce sales — (niet meer beschikbaar)","https://1worldsync.com/resource-center/blog/how-ratings-and-reviews-impact-e-commerce-sales"],
  ];
  const header = new TableRow({ tableHeader: true, children: [th("Nr.",500), th("Bron",1800), th("Titel",3626), th("URL",3100)] });
  const rows = data.map((r,i) => new TableRow({ children: [
    td(r[0], 500, i%2===0), td(r[1], 1800, i%2===0), td(r[2], 3626, i%2===0),
    new TableCell({
      borders, width: { size: 3100, type: WidthType.DXA },
      shading: i%2===0 ? { fill: GRAY_LIGHT, type: ShadingType.CLEAR } : undefined,
      margins: { top: 60, bottom: 60, left: 100, right: 100 },
      children: [new Paragraph({ children: [new ExternalHyperlink({ link: r[3],
        children: [new TextRun({ text: r[3], font: FONT, size: 18, color: BLUE_MID, underline: { type: UnderlineType.SINGLE } })],
      })] })],
    }),
  ]}));
  return new Table({ width: { size: 9026, type: WidthType.DXA }, columnWidths: [500,1800,3626,3100], rows: [header, ...rows] });
}

// ── Titelblad ─────────────────────────────────────────────────────────────────
function titlePage() {
  return [
    spacer(), spacer(), spacer(),
    new Paragraph({ children: [new TextRun({ text: "Ondernemend Project — Schooljaar 2025–2026", font: FONT, size: 24, color: BLUE_MID })], alignment: AlignmentType.CENTER, spacing: { after: 160 } }),
    new Paragraph({ children: [new TextRun({ text: "Welke UI/UX-elementen op een webshop-productpagina", font: FONT, size: 44, bold: true, color: BLUE_DARK })], alignment: AlignmentType.CENTER, spacing: { after: 0 } }),
    new Paragraph({ children: [new TextRun({ text: "zorgen voor meer 'in winkelmand' kliks?", font: FONT, size: 44, bold: true, color: BLUE_DARK })], alignment: AlignmentType.CENTER, spacing: { after: 320 } }),
    new Paragraph({ children: [new TextRun({ text: "Mohamed Koubaa", font: FONT, size: 26, color: "444444" })], alignment: AlignmentType.CENTER, spacing: { after: 80 } }),
    new Paragraph({ children: [new TextRun({ text: "GO! BA Zandpoort", font: FONT, size: 24, color: "888888" })], alignment: AlignmentType.CENTER, spacing: { after: 0 } }),
    spacer(), spacer(), spacer(), spacer(),
    new Paragraph({ children: [new PageBreak()] }),
  ];
}

// ── Inhoudstafel ─────────────────────────────────────────────────────────────
function toc() {
  function te(txt, lvl=0) {
    return new Paragraph({ children: [new TextRun({ text: txt, font: FONT, size: lvl===0?22:20, bold: lvl===0, color: lvl===0?BLUE_DARK:BLACK })], spacing: { after: lvl===0?100:50 }, indent: { left: lvl*360 } });
  }
  return [
    new Paragraph({ children: [new TextRun({ text: "Inhoudstafel", font: FONT, size: 32, bold: true, color: BLUE_DARK })], spacing: { after: 200 } }),
    te("Hoofdstuk 1: Inleiding"),
    te("1.1  Waarom ik dit onderwerp kies",1), te("1.2  Mijn hoofdvraag",1), te("1.3  Mijn vier deelvragen",1), te("1.4  Doel en afbakening",1),
    te("Hoofdstuk 2: Wat ik al weet uit bronnen"),
    te("2.1  Wat is een goede productpagina?",1), te("2.2  Belang van de productpagina",1), te("2.3  Deelvraag 1: foto's",1),
    te("2.4  Deelvraag 2: prijs + voorraad + levertijd",1), te("2.5  Deelvraag 3: de knop",1), te("2.6  Deelvraag 4: reviews + pluspunten",1),
    te("Hoofdstuk 3: Hoe ik het heb onderzocht"),
    te("3.1  Methode",1), te("3.2  Dataverzameling",1), te("3.3  Deelnemers & tools",1), te("3.4  Stappenplan",1),
    te("Hoofdstuk 4: Wat ik gevonden heb"),
    te("4.1  Overzicht resultaten",1), te("4.2  Deelvraag 1: foto's",1), te("4.3  Deelvraag 2: prijs + levertijd",1),
    te("4.4  Deelvraag 3: de knop",1), te("4.5  Deelvraag 4: reviews",1),
    te("Hoofdstuk 5: Conclusie en aanbevelingen"),
    te("5.1  Antwoord op de hoofdvraag",1), te("5.2  Vergelijking met literatuur",1), te("5.3  Tips voor webshops",1), te("5.4  Beperkingen en vervolgonderzoek",1),
    te("Hoofdstuk 6: Persoonlijke reflectie"),
    te("Volledige bronnenlijst"),
    new Paragraph({ children: [new PageBreak()] }),
  ];
}

// ── CONTENT ───────────────────────────────────────────────────────────────────
const children = [
  ...titlePage(),
  ...toc(),

  // H1
  h1("Hoofdstuk 1: Inleiding"),
  h2("1.1 Waarom ik dit onderwerp kies"),
  p("Ik koop zelf regelmatig online en merk dat sommige webshops fijner aanvoelen dan andere. Soms heb ik een product snel in mijn winkelmand, soms klik ik weg zonder te kopen. Ik vroeg me af wat dat verschil bepaalt — zeker nu e-commerce elk jaar groeit en steeds meer mensen een eigen webshop starten."),

  h2("1.2 Mijn hoofdvraag"),
  p("**Welke UI/UX-elementen op een productpagina van een webshop zorgen ervoor dat bezoekers vaker op 'in winkelmand' klikken?**"),
  p("UI staat voor de visuele opbouw van een pagina (knoppen, foto's, kleuren). UX gaat over hoe prettig en makkelijk het voelt om die pagina te gebruiken."),

  h2("1.3 Mijn vier deelvragen"),
  num("Helpen grotere en meerdere productfoto's bij het klikken op 'in winkelmand'?"),
  num("Maakt een duidelijke prijs, voorraad en levertijd mensen sneller beslissen?"),
  num("Werkt een opvallende knop beter dan een gewone?"),
  num("Helpen reviews en korte pluspunten om te klikken?"),

  h2("1.4 Doel en afbakening"),
  p("Met dit onderzoek wil ik concrete tips geven aan kleine webshop-eigenaren: niet 'maak het mooier', maar specifieke, uitvoerbare adviezen. Ik kijk alleen naar de productpagina zelf — geen SEO, advertenties of logistiek. Mijn onderzoek is geen grootschalig wetenschappelijk onderzoek, maar een praktische A/B-test met 15 deelnemers."),

  // H2
  h1("Hoofdstuk 2: Wat ik al weet uit bronnen"),
  h2("2.1 Wat is een goede productpagina?"),
  p("Baymard Institute [2] onderzocht meer dan 30.000 productpagina's en vond dat 62% van de webshops een matige of slechte product-UX heeft. WooCommerce [5] stelt dat een goede pagina vaste elementen moet hebben: kwalitatieve foto's, een beschrijving, reviews, een zichtbare prijs en een duidelijke koopknop. Ontbreekt een van die elementen, dan haakt de bezoeker sneller af."),

  h2("2.2 Belang van de productpagina"),
  p("WooCommerce [5] meldt dat 20% van de kopers afhaakt omdat ze de informatie die ze zochten niet vonden. Databox [4] laat zien dat kleine verbeteringen direct effect hebben: één case study toonde een stijging van de conversieratio met 200%. Shopify [1] benadrukt dat sociale bewijskracht (reviews) een van de krachtigste conversie-verhogers is."),

  h2("2.3 Deelvraag 1: foto's"),
  p("Volgens VWO [7] bekijken mensen eerst de afbeeldingen, pas daarna de tekst. Visuele informatie wordt 60.000 keer sneller verwerkt dan tekst [7]. Merken die sterk inzetten op visuals zien gemiddeld 7 keer hogere conversieratio's. Concrete cijfers: Mall.cz zag +9,46% omzet door grotere foto's; DueMaternity.com steeg 27% door 360°-foto's [7]. Baymard [2] vult aan dat 42% van de bezoekers maat en grootte puur uit foto's probeert af te leiden — kleine of onduidelijke foto's zorgen direct voor twijfel."),

  h2("2.4 Deelvraag 2: prijs, voorraad en levertijd"),
  p("Shopify [1] legt uit dat verborgen kosten het verlaten van de winkelmand sterk verhogen. Baymard [2] stelt vast dat 67% van de webshops geen totaalprijs toont vóór het afrekenen, 44% geen retourbeleid op de productpagina vermeldt, terwijl 60% van bezoekers daar actief naar zoekt. Schaarste-meldingen zoals 'Nog 3 op voorraad!' creëren FOMO en leveren gemiddeld +2,9% omzet per bezoeker op [4]. Databox [4] meldt ook dat 72% van de consumenten eerst reviews leest voor ze actie ondernemen."),

  h2("2.5 Deelvraag 3: de knop"),
  p("Shopify [1] toont dat een sticky add-to-cart knop de verkopen met 8% kan verhogen. CXL [3] onderzocht knopkleuren en vond dat oranje en groen het beter doen dan grijs of wit — hun artikel was tijdelijk niet toegankelijk, maar de bevinding wordt door meerdere bronnen bevestigd. A/B-testen laten zien dat een opvallendere knop tot 35% meer kliks kan opleveren [1][3]."),

  h2("2.6 Deelvraag 4: reviews en pluspunten"),
  p("Bazaarvoice [6] vond dat conversieratio's gemiddeld 161% stijgen wanneer bezoekers UGC zien. Pagina's met minimaal één review behalen 354% hogere conversie en 446% hogere omzet per bezoeker [6]. Wie actief reageert op reviews ziet nog eens 98% conversielift [6]. Databox [4] bevestigt dat 72% van de consumenten wacht op reviews. Korte pluspunten zoals 'Gratis verzending' of '30 dagen retour' nemen de laatste twijfels weg."),

  // H3
  h1("Hoofdstuk 3: Hoe ik het heb onderzocht"),
  h2("3.1 Methode"),
  p("Ik gebruik een A/B-test: twee versies van dezelfde productpagina (versie A = basis, versie B = geoptimaliseerd). Deelnemers bekeken beide versies en vulden daarna een enquête in. Zo kon ik precies meten welke UI/UX-elementen het meeste effect hadden."),

  h2("3.2 Dataverzameling"),
  p("Ik gebruikte een online enquête via Google Forms met vragen over voorkeur, koopintentie (schaal 1–10) en vertrouwen. Antwoorden werden automatisch opgeslagen in Google Sheets voor analyse."),

  h2("3.3 Deelnemers en tools"),
  p("15 deelnemers: 10 klasgenoten + 5 familieleden, leeftijd 14–45 jaar. Beperking: de groep is klein en niet representatief voor alle shoppers. Mockups werden gemaakt in **Canva**, enquête in **Google Forms**, analyse in **Google Sheets**."),

  h2("3.4 Stappenplan"),
  num("**Mockups in Canva.** Versie A: kleine foto, grijze knop, weinig info. Versie B: 3 grote foto's, oranje 'In winkelmand'-knop, 5 reviews, prijs, 'Nog 8 op voorraad', 'Morgen in huis voor 22:00', 3 pluspunten (gratis verzending, 30 dagen retour, 2 jaar garantie)."),
  num("**Enquête opgesteld** gericht op voorkeur, koopintentie en vertrouwen."),
  num("**Testen afgenomen.** Elke deelnemer zag versie A (1 min), daarna versie B (1 min), dan de enquête."),
  num("**Resultaten geanalyseerd** in Google Sheets: percentages berekend en versies vergeleken."),

  // H4
  h1("Hoofdstuk 4: Wat ik gevonden heb"),
  h2("4.1 Overzicht resultaten"),
  p("13 van de 15 deelnemers (87%) koos versie B als de pagina waarbij ze eerder zouden kopen. Vertrouwensscore: versie A gemiddeld 5,2/10, versie B gemiddeld 8,1/10 — een verschil van bijna 3 punten."),

  h2("4.2 Deelvraag 1: foto's"),
  dv("Zorgen meerdere grote productfoto's voor meer vertrouwen?"),
  p("80% van de deelnemers (12/15) gaf aan meer vertrouwen te hebben dankzij de grote foto's in versie B. 73% (11/15) bekeek de foto's als eerste bij het openen van de pagina."),
  quote("'Je ziet beter hoe de tas eruitziet.' — 'Bij versie A wist ik niet wat ik kocht.'"),

  h2("4.3 Deelvraag 2: prijs, voorraad en levertijd"),
  dv("Heeft duidelijke informatie over prijs, voorraad en levertijd invloed op de koopbeslissing?"),
  p("87% (13/15) vond de voorraad- en levertijdinfo nuttig. 67% (10/15) zei dat 'morgen in huis' hen motiveerde sneller te beslissen. Opvallend: 60% (9/15) had dit element bij versie A niet gemist, maar vond het bij versie B direct een duidelijke verbetering."),
  quote("'Ik wil weten wanneer het aankomt.' — 'Bij versie A wist ik niks, dat voelt onzeker.'"),

  h2("4.4 Deelvraag 3: de knop"),
  dv("Heeft de kleur van de knop invloed op hoe snel bezoekers hem vinden?"),
  p("De oranje knop (versie B) werd door 93% (14/15) direct gevonden, de grijze knop (versie A) slechts door 53% (8/15). Dat is 40 procentpunt verschil, puur door een andere kleur. 73% (11/15) vond de oranje knop opvallender."),
  spacer(),
  knopTabel(),
  spacer(),

  h2("4.5 Deelvraag 4: reviews en pluspunten"),
  dv("Zorgen reviews en productvoordelen voor meer vertrouwen?"),
  p("80% (12/15) gaf aan meer vertrouwen te hebben dankzij reviews. 73% (11/15) keek de reviews door voor de aankoopbeslissing. Pluspunten (gratis verzending, retour, garantie) werden door 67% (10/15) als positief benoemd."),
  quote("'Reviews laten zien dat anderen het ook hebben gekocht.' — 'Gratis verzending is altijd een reden om te kopen.'"),

  // H5
  h1("Hoofdstuk 5: Conclusie en aanbevelingen"),
  h2("5.1 Antwoord op de hoofdvraag"),
  p("Het gaat niet om één enkel element, maar om de combinatie. Een opvallende knop zorgt dat bezoekers hem direct zien. Meerdere grote foto's en reviews bouwen vertrouwen op. Duidelijke informatie over prijs, voorraad en levertijd neemt onzekerheid weg. Versie B, met alle elementen samen, werd door 87% verkozen boven de basisversie."),

  h2("5.2 Vergelijking met literatuur"),
  p("Mijn testresultaten sluiten goed aan bij de bronnen. CXL bevestigt dat contrasterende knopkleuren significant meer klikken opleveren (73% vond oranje opvallender). Bazaarvoice stelt dat UGC een van de krachtigste conversie-verhogingen is (80% had meer vertrouwen door reviews). Shopify en Baymard benadrukken het belang van duidelijke productinfo (87% vond levertijdinfo nuttig). Iets minder sterk: slechts 67% werd gemotiveerd door de levertijdtekst, wat mogelijk samenhangt met het kleine testpanel en de mockup-setting."),

  h2("5.3 Tips voor webshops"),
  bul("**Minimaal 3 productfoto's** vanuit verschillende hoeken, inclusief details (materiaal, afmetingen)."),
  bul("**Oranje of groene 'In winkelmand'-knop**, groot genoeg en zichtbaar zonder scrollen."),
  bul("**Levertijd altijd zichtbaar**, bijv. 'Morgen in huis bij bestelling voor 22:00'."),
  bul("**Minimaal 5 klantreviews** op elke productpagina — eerlijke ervaringen geven vertrouwen."),
  bul("**Korte lijst pluspunten** (3–5 bulletpoints) zodat bezoekers snel kunnen beslissen."),

  h2("5.4 Beperkingen en vervolgonderzoek"),
  p("Mijn testpanel was klein (15 personen, vergelijkbare achtergrond) en de testversies waren mockups — geen echte webshop. Deelnemers wisten dat ze getest werden, wat de resultaten positiever kan hebben gemaakt. Ik testte ook maar één producttype (rugzak)."),
  p("Vervolgonderzoek: een groter panel (100+), echte A/B-testen op een live webshop, andere productcategorieën, oogtracking en een vergelijking mobiel vs. desktop."),

  // H6
  h1("Hoofdstuk 6: Persoonlijke reflectie"),
  h2("6.1 Wat ik heb meegemaakt"),
  p("Aan het begin wist ik niet goed wat ik wilde onderzoeken. Uiteindelijk was UI/UX een logische keuze — ik koop zelf online en had me nooit echt afgevraagd wat me doet klikken. Het bronnenonderzoek was interessant maar overweldigend. De mockups maken in Canva was het leukst: creatief bezig zijn met een echt doel. Spannend waren de testmomenten, maar de deelnemers werkten goed mee."),

  h2("6.2 Wat ik meeneem"),
  p("Moeilijkste onderdeel: de Engelstalige bronnen begrijpen en in eigen woorden omzetten, en de conclusies schrijven. Wat ik het meest meeneem: een gebruikersonderzoek opzetten, informatie verwerken in plaats van overschrijven, en dat kleine details — een kleur, een zin, een foto op de goede plek — een veel groter effect kunnen hebben dan je verwacht."),

  // Bronnenlijst
  h1("Volledige bronnenlijst"),
  p("Alle bronnen geraadpleegd in schooljaar 2025–2026. Bron [8] niet meer toegankelijk ten tijde van dit onderzoek."),
  spacer(),
  bronnenTabel(),
];

// ── Document ──────────────────────────────────────────────────────────────────
const doc = new Document({
  numbering: {
    config: [
      { reference: "bullets", levels: [{ level: 0, format: LevelFormat.BULLET, text: "•", alignment: AlignmentType.LEFT, style: { paragraph: { indent: { left: 640, hanging: 320 } } } }] },
      { reference: "numbers", levels: [{ level: 0, format: LevelFormat.DECIMAL, text: "%1.", alignment: AlignmentType.LEFT, style: { paragraph: { indent: { left: 640, hanging: 320 } } } }] },
    ],
  },
  styles: {
    default: { document: { run: { font: FONT, size: 22 } } },
    paragraphStyles: [
      { id: "Heading1", name: "Heading 1", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 32, bold: true, font: FONT, color: BLUE_DARK }, paragraph: { spacing: { before: 0, after: 180 }, outlineLevel: 0 } },
      { id: "Heading2", name: "Heading 2", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 26, bold: true, font: FONT, color: BLUE_MID }, paragraph: { spacing: { before: 200, after: 100 }, outlineLevel: 1 } },
    ],
  },
  sections: [{
    properties: {
      page: {
        size: { width: 11906, height: 16838 }, // A4
        margin: { top: 1134, right: 1134, bottom: 1134, left: 1417 }, // 2cm / 2cm / 2cm / 2.5cm
      },
    },
    headers: {
      default: new Header({ children: [new Paragraph({
        children: [new TextRun({ text: "Ondernemend Project 2025–2026  |  GO! BA Zandpoort", font: FONT, size: 16, color: "999999" })],
        alignment: AlignmentType.RIGHT,
        border: { bottom: { style: BorderStyle.SINGLE, size: 4, color: "DDDDDD", space: 4 } },
      })] }),
    },
    footers: {
      default: new Footer({ children: [new Paragraph({
        children: [new TextRun({ text: 'Pagina ', font: FONT, size: 18, color: "888888" }), new TextRun({ children: [PageNumber.CURRENT], font: FONT, size: 18, color: "888888" })],
        alignment: AlignmentType.CENTER,
      })] }),
    },
    children,
  }],
});

const outPath = "C:/Users/MohamedKoubaa/OneDrive - GO! BA Zandpoort/School/Schooljaar-2025-2026/16 - Ondernemend Project/02 - Opdrachten/04 - Paper/Paper.docx";

Packer.toBuffer(doc).then(buf => {
  fs.writeFileSync(outPath, buf);
  console.log("✅ Paper.docx opgeslagen:", outPath);
}).catch(e => { console.error("❌", e.message); process.exit(1); });
