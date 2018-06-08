# Terminsystem

Dieses Porgramm ist ein Eventplaner mit dem man abstimmen kann wann ein Event stattfinden soll

## Getting Started

Hier wird erklärt, wie man seinen eigenen Terminsystem Server kompiliert und aufsetzt.

### Prerequisites

* [Gradle](https://gradle.org/)
* [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

### Installing

1) Zuerst muss das git-Repository heruntergeladen werden. Das kann entweder über Download>Downlad Zip oder diesen Befehl geschehen:

```bash
 git clone git@github.com:rwutscher-tgm/terminsystem.git
 ```

2) Als nächstes muss man in den Ordner gehen und den gradle build starten.

```bash
cd terminsystem
gradle build
 ```

3) Letztens muss ein jar File mit dem folgenden Befehl erstellt werden

```bash
gradle jar
 ```

4) Das jar-File befindet sich jetzt unter build/libs


## Running the tests



## Authors

* **Richard Wutscher** - *Brain* - [rwutscher-tgm](https://github.com/rwutscher-tgm)
* **Nemanja Tesanovic** - *Pinky* - [ntesanovic-tgm](https://github.com/ntesanovic-tgm)
* **Ben Schlegel** - *Ant* - [bschlegel-tgm](https://github.com/bschlegel-tgm)
* **Florian Medved** - *Ant* - [fmedved](https://github.com/fmedved)


See also the list of [contributors](https://github.com/rwutscher-tgm/terminsystem/contributors) who participated in this project.

## Errors:

* First login redirects to error but logs in
