![enter image description here](https://github.com/ArielNATAF/Projet-PSI-M2/raw/dev/logo.png)
![GPLv3 logo button](http://gplv3.fsf.org/gplv3-88x31.png)
# SuperBPMN

Projet du cours de PSI de M2 MIAGE à Paris Nanterre en 2018-2019, par Ariel NATAF.

Pour voir l'application fonctionner, regardez la vidéo :

[![Oscar Nominated video in "PSI presentation" category](http://img.youtube.com/vi/9WDrkakHZPA/0.jpg)](https://www.youtube.com/watch?v=9WDrkakHZPA)

## SuperBPMN ?

L'objectif était de faire un programme qui permettrait d'éxécuter des vérifications de fichiers BPMN.
Ces vérifications se feraient par des programmes contenus dans des Docker pour permettre une modularité de ces vérifications.

## Fonctionnalités

Version 1.0.0.beta

 - Vérifier l'extension d'un fichier BPMN.
 - Voir l'historique des vérifications.

## Installation

### Pré-requis

 - `Docker` ;
 - `yarn ou NPM` pour l'interface générée par reactjs ;
 - `Maven` pour la partie en Spring ;
 - `Mongodb` ;
 - cloner le dépôt.

### Avec IntelliJ

Si vous utilisez **IntelliJ**, ouvrez le projet qui est contenu dans le dossier SuperBPMN (ne pas confondre avec le dossier superbpmnio).
Laissez IntelliJ cofigurer le projet.

### Créer une Image Docker

 - Depuis un terminal, allez à `superBPMN/ressources/docker verif extension`;
 - Créez une image Docker avec la commande `docker build ./`
 
 Vous devriez voir un message similaire au suivant  s'afficher :
>  Sending build context to Docker daemon   7.68kB Step 1/4 : FROM
> python:slim  ---> 96c349a2897f Step 2/4 : ADD verif.py /  ---> Using
> cache  ---> fdf4d4632b63 Step 3/4 : ADD result.txt /  --->
> 7fb95e3b11fb Step 4/4 : ENTRYPOINT [ "python", "./verif.py" ]  --->
> Running in d1e64e46df52 Removing intermediate container d1e64e46df52 
> ---> ab0352d9dd38 Successfully built ab0352d9dd38

*ab0352d9dd38* est l'identifant de l'image docker généré ici. Il est unique à chaque installation.

 - Copiez l'identifant de l'image Docker.
 - Allez sur IntelliJ, et accédez au fichier `superBPMN/src/main/java/com/example/superBPMN/Model/DockerEnum.java`
 - remplacez dans `VERIF(new DockerImage("testcopycontainer",  
  "ressources/docker verif extension/Dockerfile",  
  "6777378f4263", "./result.txt",  
  "ressources/docker verif extension/result.txt"))` *6777378f4263* par l'identifant de l'image Docker générée.

### Installer MongoDB (sur Mac avec homebrew)

Dans le terminal, entrez `brew install mongodb`

 ### Lancer l'interface la partie ;

  ### Lancer l'application (avec Yarn) ;

 - Dans le terminal, lancez la commande `brew services start mongo`
 - Dans intelliJ appuyez sur « Run »![enter image description here](https://www.jetbrains.com/help/img/idea/2018.3/icons.actions.execute.svg@2x.png) ;
 - Via un terminal, allez dans le répertoire **superbpmnio** ; 
 - Lancez la commande `yarn start` ;
 - Allez dans `localhost:3000`

### Utiliser la vérification d'extension de fichier .bpmn

 - Via l'interface de `localhost:3000`, appuyez sur « choisir » ;
 - Le résultat de la vérification s'affiche quand on rafraîchit la page.
