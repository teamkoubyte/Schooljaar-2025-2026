#!/bin/bash

echo "Wat is de naam van je website?"
read websiteNaam

mkdir $websiteNaam && cd $websiteNaam
touch index.html style.css script.js
mkdir images/ blogposts/

echo "De bestanden en folders zijn aangemaakt"
tree

echo "Hoeveel blogposts wil je hebben?"
read aantalBlogposts

for ((i=1; i<=aantalBlogposts; i++))
do
	cat <<EOF > "blogposts/post-${i}.html"
<!DOCTYPE html>
<html lang="nl">
<head>
	<meta charset="UTF-8">
	<title>Post $i</title>
	<link rel="stylesheet" href="../style.css">
</head>
<body>
	<h1>Blogbericht $1</h1>
	<p>Inhoud voor post $i komt hier.</p>
	<a href="../index.html">Terug naar home</a>
</body>
</html>
EOF
done

echo "Er zijn $aantalBlogposts blogposts aangemaakt in de map blogposts/."
