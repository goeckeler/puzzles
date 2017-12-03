# Professor Krawinkels Weihnachtsschrank

Der Herr Professor Krawinkel ist ein wenig komischer Kauz. Trotzdem sehr liebenswert, denn jedes Jahr gibt es Geschenke für die Studenten. Nur dieses Jahr sieht es nicht gut aus, denn Professor Krawinkel wurde zu Hause einfach eingeschneit. Er hat Dich angerufen, damit Du die Geschenke austeilst, die in einem Kämmerchen aufbewahrt werden. Auf einen Zettel solltest Du eine Liste von Wörtern aufschreiben. An der Kammertür hängt ein Codeschloss und ein Zettel mit Buchstaben. „Finde die Position der Wörter und die Windrichtung, in der sie zeigen. Die Zahl ist immer die Position des Buchstaben im Alphabet. Das ist der Eingabecode.“, und dann wurde die Verbindung unterbrochen.

## Eingabe

Du bekommst einen Buchstabensalat und eine Liste mit Wörtern. Das sieht dann zum Beispiel so aus:

    NALE
    PFAL
    EFIL 
    FETA
    
    AFFE
    ELAN
    FETA
    ALLE

Der Buchstabensalat hat garantiert in jeder Zeile die gleiche Anzahl Buchstaben und auch in jeder Spalte. Die Wörter können unterschiedlich lang sein. Es werden nur Großbuchstaben verwendet.

## Beispiel

Im obigen Beispiel findest Du die Wörter wie folgt, die Windrichtungen sind Norden, Westen, Süden und Osten.

    (1,2,S)-> AFFE
    (1,4,W)-> ELAN
    (4,1,O)-> FETA
    (4,4,N)-> ALLE

Der erste Buchstabe im Alphabet ist „A“, der zweite „B“, der vierte „D“, und so weiter.

## Ausgabe

Die Positionen sind in der Reihenfolge der Wörter auszuwerten und in eine einzige Zeile zu schreiben. Für das oben genannte Beispiel sollte die einzige Ausgabe des Programmes lauten:


    ABSADWDAODDN

Kein Wunder, dass der Professor sich lieber ein paar Wörter gemerkt hat. Viel Erfolg!
