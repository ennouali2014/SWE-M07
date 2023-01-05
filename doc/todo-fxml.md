One approach is to encapsulate each of your tab pages into a separate FXML file with it's own associated controller
class.
Then in your FXML file for the main tab control you can do something like this:

Ein Ansatz besteht darin, jede Ihrer Registerkarten in eine separate FXML-Datei mit einer eigenen zugehörigen
Controller-Klasse zu kapseln.
In Ihrer FXML-Datei für die Hauptregisterkarte können Sie dann etwas wie folgt tun:

    <TabPane fx:controller="com.foo.MainController">
        <tabs>
            <Tab text="Untitled Tab 1">
                <content>
                    <fx:include fx:id="fooTabPage" source="fooTabPage.fxml"/>
                </content>
            </Tab>
            <Tab text="Untitled Tab 2">
                <content>
                    <fx:include fx:id="barTabPage" source="barTabPage.fxml"/>
                </content>
            </Tab>
        </tabs>
    </TabPane>

Notice that instead of embedding the content directly, I am using the fx:include directive which tells the FXMLLoader to
load the FXML file that is being referenced. The individual FXML files used for the page content will all have their own
controller so that the logic is nicely separated.
If you need to interact with the sub-pages or sub-controllers from the main controller then you can reference them like
you do with any other FXML control to have them injected.

Beachten Sie, dass ich den Inhalt nicht direkt einbette, sondern die Direktive fx:include verwende, die den FXMLLoader
anweist, die referenzierte FXML-Datei zu laden. Die einzelnen FXML-Dateien, die für den Seiteninhalt verwendet werden,
haben alle ihren eigenen Controller, so dass die Logik sauber getrennt ist.
Wenn Sie vom Hauptcontroller aus mit den Unterseiten oder Untercontrollern interagieren müssen, können Sie sie wie jedes
andere FXML-Steuerelement referenzieren, damit sie injiziert werden.

    public class MainController {
    // Inject tab content.
    @FXML private FooTabPage fooTabPage;
    // Inject controller
    @FXML private FooTabController fooTabPageController;

    // Inject tab content.
    @FXML private BarTabPage barTabPage;
    // Inject controller
    @FXML private BarTabController barTabPageController;
    }

If you have a large number of pages (each with a large number of their own controls) another approach is to leave each
tab empty, and once the main view is loaded, load the relevant page into your control.
You would need to listen for tab changes to switch the content and add relevant code to load / unload the views that are
being used for the content of the tab pages.
I would recommend starting with the first approach and refactoring to use the second approach if you discover
performance problems.

Wenn Sie eine große Anzahl von Seiten haben (jede mit einer großen Anzahl von eigenen Steuerelementen), besteht ein
anderer Ansatz darin, jede Registerkarte leer zu lassen und, sobald die Hauptansicht geladen ist, die entsprechende
Seite in Ihr Steuerelement zu laden.
Sie müssten auf Änderungen der Registerkarten achten, um den Inhalt zu wechseln, und entsprechenden Code zum
Laden/Entladen der Ansichten hinzufügen, die für den Inhalt der Registerkarten verwendet werden.
Ich würde empfehlen, mit dem ersten Ansatz zu beginnen und die Umstellung auf den zweiten Ansatz vorzunehmen, wenn Sie
Leistungsprobleme feststellen

Hier ein Link mit einer Zusammenfassung verschiedener Lösungswege:
https://itecnote.com/tecnote/javafx-8-tabpanes-and-tabs-with-separate-fxml-and-controllers-for-each-tab/
