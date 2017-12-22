!!Attn: les noms des classes, des variables et les commentaires sont en anglais !!
!!Les resultats sont affiches en anglais!!

Classes: ParisMetro, HeapManipulator, Graph
1-Le graph est representee avec une liste d'adjacence
2- algorithme de plus court chemin inspire de : net.datastructures.GraphAlgorithms et powerpoint de cours
3- methode read metro inspire de net.datastructures.SimpleGraph
4- algorithme de HeapManipulator inspire de l'algortihme de heapsort sur le site du cours


-metroLine(v) ajoute tous les sommets adjacents a "v" qui ne sont pas connectees entre eux par un arrete de valeur (-1) dans une liste et retourne cette liste. 
-L'algorithme de plus court chemin utilise un heap et utilise des attributs dans les sommets pour identifier: si un sommet est dans le nuage(inCloud), si un sommet est disponible(available) et le numero de sommet qui precede un sommet(previousVertexNumber) dans le plus court chemin (pour pouvoir afficher le chemin)
-Pour mettre une ligne hors fonction, il suffit d'utilser la methode de metroLine(v) qui retourne une liste puis changer tous les attributs "v.setAvailable(false)" des stations dans la ligne, et elles seront ignores dans le processus de relaxation.

Exemples d'utilisation:

entree : ParisMetro 19
sortie: Path:	214 236 19 93 97 23 287 62 216 243 83 128 324 96 293 101 209 232 348 45 155 98 26 230 354 30 150 57

entree : ParisMetro 203 368
sortie: Path:	203 202 51 137 359 321 100 207 206 106 231 368 --- Time: 11.0833333 minutes (665 secondes)

entree : ParisMetro 203 368 15
sortie: 
	Closed line:	72 188 187 273 249 234 113 207 100 321 359 137 51 202 326 168 245 153 131 272 126 182 48 318 15 319
	
	Path:	203 332 110 109 50 77 78 9 338 308 347 294 218 206 106 231 368 --- Time: 12.65 minutes (759 secondes)