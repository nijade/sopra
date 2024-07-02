package com.example.sopra.service;

import com.example.sopra.entity.CareInstruction;
import com.example.sopra.entity.Plant;
import com.example.sopra.entity.Quiz;
import com.example.sopra.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private CareInstructionService careInstructionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Initialisiere Testdaten auf Datenbank...");

        // Nutzer 1
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("1234"));
        user1.setSales(7);
        user1.setLevel(15);
        userService.saveUser(user1);

        // Nutzer 2
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("5678"));
        user2.setSales(8);
        user2.setBuys(4);
        userService.saveUser(user2);

        // Nutzer 3
        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword(passwordEncoder.encode("abcd"));
        user3.setSales(12);
        userService.saveUser(user3);

        // Nutzer 4
        User user4 = new User();
        user4.setUsername("user4");
        user4.setPassword(passwordEncoder.encode("efgh"));
        userService.saveUser(user4);

        // Nutzer 5
        User user5 = new User();
        user5.setUsername("user5");
        user5.setPassword(passwordEncoder.encode("ijkl"));
        userService.saveUser(user5);

        // Nutzer 6
        User hans = new User();
        hans.setUsername("Hans");
        hans.setPassword(passwordEncoder.encode("0987"));
        hans.setName("Hans Bauer");
        hans.setEmail("bauer@exmaple.com");
        hans.setAge(33);
        hans.setGender("M\u00e4nnlich");
        hans.setProfileDescription("aufgewachsen in stuttgart, Sicherheitsingenieur, Naturfreund");
        hans.setProfileImage("profilbild_schafStrecktZunge.jpeg");
        userService.saveUser(hans);


        //Beispielpflanzen ***********************************************************************+
        // Beispielpflanze: Rose

        Plant rose = new Plant();
        rose.setTitle("rote Rose");
        rose.setPhotos(Arrays.asList("rose.jpg"));
        rose.setHeight(50);
        rose.setPrice(15.99);
        rose.setHasPlanter(true);
        rose.setDescription("Eine wunderschöne rote Rose.");
        rose.setPotCircumference(20.0);
        rose.setPlantCircumference(30.0);
        rose.setTags(Arrays.asList("Sommerblumen", "Rosen", "Garten"));
        rose.setSeller(user1);
        plantService.savePlant(rose);

        // Tulpen
        // Tulpe groß
        Plant tulip = new Plant();
        tulip.setTitle("Tulpe");
        tulip.setPhotos(Arrays.asList("tulip.jpg"));
        tulip.setHeight(30);
        tulip.setPrice(8.99);
        tulip.setHasPlanter(false);
        tulip.setDescription("Eine bunte Tulpe, ideal für den Frühlingsgarten.");
        tulip.setPotCircumference(0.0);
        tulip.setPlantCircumference(10.0);
        tulip.setTags(Arrays.asList("Blume", "Fruehling", "Garten"));
        tulip.setSeller(user2);
        plantService.savePlant(tulip);

        // Tulpe medium
        Plant tulipMedium = new Plant();
        tulipMedium.setTitle("Tulpe Medium");
        tulipMedium.setPhotos(Arrays.asList("tulip.jpg"));
        tulipMedium.setHeight(20);
        tulipMedium.setPrice(8.99);
        tulipMedium.setHasPlanter(false);
        tulipMedium.setDescription("Eine bunte medium Tulpe, ideal für den Frühlingsgarten.");
        tulipMedium.setPotCircumference(0.0);
        tulipMedium.setPlantCircumference(10.0);
        tulipMedium.setTags(Arrays.asList("Blume", "Fruehling", "Garten"));
        tulipMedium.setSeller(user2);
        plantService.savePlant(tulipMedium);

        // Weiße Spätblühende Tulpe
        Plant tulipThree = new Plant();
        tulipThree.setTitle("Weiße Spaetbluehende Tulpe");
        tulipThree.setPhotos(Arrays.asList("tulip.jpg"));
        tulipThree.setHeight(45);
        tulipThree.setPrice(12.99);
        tulipThree.setHasPlanter(true);
        tulipThree.setDescription("Eine spaetbluehende weiße Tulpe, perfekt für den Spätsommergarten.");
        tulipThree.setPotCircumference(12.0); // Beispiel für den Pflanztopfumfang
        tulipThree.setPlantCircumference(13.5);
        tulipThree.setTags(Arrays.asList("Blume", "Sommer", "Garten"));
        tulipThree.setSeller(user1);
        plantService.savePlant(tulipThree);

        // Pinkfarbene Zwergtulpe
        Plant tulipFour = new Plant();
        tulipFour.setTitle("Pinkfarbene Zwergtulpe");
        tulipFour.setPhotos(Arrays.asList("tulip.jpg"));
        tulipFour.setHeight(20);
        tulipFour.setPrice(7.49);
        tulipFour.setHasPlanter(true);
        tulipFour.setDescription("Eine niedrige pinkfarbene Zwergtulpe, ideal für den Vorgarten.");
        tulipFour.setPotCircumference(8.0); // Beispiel für den Pflanztopfumfang
        tulipFour.setPlantCircumference(8.0);
        tulipFour.setTags(Arrays.asList("Blume", "Fruehling", "Zwiebel- und Knollenpflanzen"));
        tulipFour.setSeller(user2);
        plantService.savePlant(tulipFour);

        // Orangene Darwin-Tulpe
        Plant tulipFive = new Plant();
        tulipFive.setTitle("Orangene Darwin-Tulpe");
        tulipFive.setPhotos(Arrays.asList("tulip.jpg"));
        tulipFive.setHeight(38);
        tulipFive.setPrice(11.29);
        tulipFive.setHasPlanter(false);
        tulipFive.setDescription("Eine kräftige orangefarbene Darwin-Tulpe, die im Frühling aufblüht.");
        tulipFive.setPotCircumference(0.0);
        tulipFive.setPlantCircumference(11.0);
        tulipFive.setTags(Arrays.asList("Blume", "Fruehling", "Garten"));
        tulipFive.setSeller(user1);
        plantService.savePlant(tulipFive);

        // Gefüllte Lilien-Tulpe
        Plant tulipSix = new Plant();
        tulipSix.setTitle("Gefuellte Lilien-Tulpe");
        tulipSix.setPhotos(Arrays.asList("tulip.jpg"));
        tulipSix.setHeight(32);
        tulipSix.setPrice(9.99);
        tulipSix.setHasPlanter(true);
        tulipSix.setDescription("Eine prächtige gefüllte Lilien-Tulpe, die im Frühling besonders schön blüht.");
        tulipSix.setPotCircumference(10.0); // Beispiel für den Pflanztopfumfang
        tulipSix.setPlantCircumference(10.5);
        tulipSix.setTags(Arrays.asList("Blume", "Fruehling", "Zwiebel- und Knollenpflanzen"));
        tulipSix.setSeller(user2);
        plantService.savePlant(tulipSix);

        // Zweifarbige Rembrandt-Tulpe
        Plant tulipSeven = new Plant();
        tulipSeven.setTitle("Zweifarbige Rembrandt-Tulpe");
        tulipSeven.setPhotos(Arrays.asList("tulip.jpg"));
        tulipSeven.setHeight(40);
        tulipSeven.setPrice(10.79);
        tulipSeven.setHasPlanter(false);
        tulipSeven.setDescription("Eine elegante zweifarbige Rembrandt-Tulpe, die im Mai blüht.");
        tulipSeven.setPotCircumference(0.0);
        tulipSeven.setPlantCircumference(12.0);
        tulipSeven.setTags(Arrays.asList("Blume", "Sommerblumen", "Garten"));
        tulipSeven.setSeller(user1);
        plantService.savePlant(tulipSeven);

        // Gefranste Papageien-Tulpe
        Plant tulipEight = new Plant();
        tulipEight.setTitle("Gefranste Papageien-Tulpe");
        tulipEight.setPhotos(Arrays.asList("tulip.jpg"));
        tulipEight.setHeight(36);
        tulipEight.setPrice(11.59);
        tulipEight.setHasPlanter(true);
        tulipEight.setDescription("Eine spektakuläre gefranste Papageien-Tulpe, die im Frühling für Aufsehen sorgt.");
        tulipEight.setPotCircumference(12.0); // Beispiel für den Pflanztopfumfang
        tulipEight.setPlantCircumference(12.5);
        tulipEight.setTags(Arrays.asList("Blume", "Fruehling", "Zwiebel- und Knollenpflanzen"));
        tulipEight.setSeller(user2);
        plantService.savePlant(tulipEight);

        // Gelbe Triumph-Tulpe
        Plant tulipNine = new Plant();
        tulipNine.setTitle("Gelbe Triumph-Tulpe");
        tulipNine.setPhotos(Arrays.asList("tulip.jpg"));
        tulipNine.setHeight(30);
        tulipNine.setPrice(9.79);
        tulipNine.setHasPlanter(false);
        tulipNine.setDescription("Eine leuchtende gelbe Triumph-Tulpe, die im April blüht.");
        tulipNine.setPotCircumference(0.0);
        tulipNine.setPlantCircumference(10.0);
        tulipNine.setTags(Arrays.asList("Blume", "Fruehling", "Gemuese"));
        tulipNine.setSeller(user1);
        plantService.savePlant(tulipNine);

        // Weiße Gefüllte Viridiflora-Tulpe
        Plant tulipTen = new Plant();
        tulipTen.setTitle("Weiße Gefuellte Viridiflora-Tulpe");
        tulipTen.setPhotos(Arrays.asList("tulip.jpg"));
        tulipTen.setHeight(35);
        tulipTen.setPrice(10.49);
        tulipTen.setHasPlanter(true);
        tulipTen.setDescription("Eine elegante weiße gefüllte Viridiflora-Tulpe mit grünen Akzenten.");
        tulipTen.setPotCircumference(10.0); // Beispiel für den Pflanztopfumfang
        tulipTen.setPlantCircumference(11.0);
        tulipTen.setTags(Arrays.asList("Blume", "Fruehling", "Zwiebel- und Knollenpflanzen"));
        tulipTen.setSeller(user2);
        plantService.savePlant(tulipTen);

        // Kaktus
        Plant cactus = new Plant();
        cactus.setTitle("Kakteen Echinopsis Lobivia cacti hybrid Lobivia cactus");
        cactus.setPhotos(Arrays.asList("cactus.jpg", "cactus2.jpg"));
        cactus.setHeight(40);
        cactus.setPrice(90.49);
        cactus.setHasPlanter(true);
        cactus.setDescription("");
        cactus.setPotCircumference(30.0);
        cactus.setPlantCircumference(20.0);
        cactus.setTags(Arrays.asList("Kuebelpflanzen", "Sommerblumen", "Zimmerpflanzen"));
        cactus.setSeller(user3);
        plantService.savePlant(cactus);

        // Palme
        Plant palme = new Plant();
        palme.setTitle("Yucca Palme");
        palme.setPhotos(Arrays.asList("yucca-palme.jpg"));
        palme.setHeight(110);
        palme.setPrice(40.00);
        palme.setHasPlanter(true);
        palme.setDescription("Die Studenten-Pflanze: Pflegeleicht und prachtvoll.");
        palme.setPotCircumference(36.0);
        palme.setPlantCircumference(70.0);
        palme.setTags(Arrays.asList("Baeume und Straeucher", "Sommerblumen", "Zimmerpflanzen"));
        palme.setSeller(hans);
        plantService.savePlant(palme);


        //*******************
        //Care Instruction
        CareInstruction summerPlantInstruction = new CareInstruction();
        summerPlantInstruction.setTagTitle("Sommerblumen");
        summerPlantInstruction.setWater("Sommerblumen regelmäßig gießen, die Erde sollte stets leicht feucht bleiben.");
        summerPlantInstruction.setFertilize("Wöchentlich mit einem Blühpflanzendünger düngen, um eine reiche Blüte zu fördern.");
        summerPlantInstruction.setLocation("Ein sonniger bis halbschattiger Platz fördert das Blühen und die Gesundheit der Pflanzen.");
        careInstructionService.save(summerPlantInstruction);

        CareInstruction balconyFlowerInstruction = new CareInstruction();
        balconyFlowerInstruction.setTagTitle("Balkonblumen");
        balconyFlowerInstruction.setWater("Balkonblumen regelmäßig gießen, besonders während heißer und trockener Perioden.");
        balconyFlowerInstruction.setFertilize("Einmal pro Woche mit einem flüssigen Blumendünger düngen.");
        balconyFlowerInstruction.setLocation("Einen sonnigen bis halbschattigen Standort wählen, der vor starkem Wind geschützt ist.");
        careInstructionService.save(balconyFlowerInstruction);

        CareInstruction medicinalPlantInstruction = new CareInstruction();
        medicinalPlantInstruction.setTagTitle("Heilpflanzen");
        medicinalPlantInstruction.setWater("Heilpflanzen nur mäßig gießen, die Erde zwischen den Wassergaben leicht antrocknen lassen.");
        medicinalPlantInstruction.setFertilize("Im Frühjahr und Sommer alle zwei Wochen mit einem organischen Dünger versorgen.");
        medicinalPlantInstruction.setLocation("Ein heller, sonniger Standort fördert das Wachstum und die Wirkstoffbildung.");
        careInstructionService.save(medicinalPlantInstruction);

        CareInstruction wildPlantInstruction = new CareInstruction();
        wildPlantInstruction.setTagTitle("Wildpflanzen");
        wildPlantInstruction.setWater("Wildpflanzen benötigen meist nur wenig zusätzliche Bewässerung, je nach Art.");
        wildPlantInstruction.setFertilize("Düngen ist selten notwendig, natürliche Böden bieten oft genug Nährstoffe.");
        wildPlantInstruction.setLocation("Ein naturnaher Standort mit ausreichend Licht und Platz zur Ausbreitung ist ideal.");
        careInstructionService.save(wildPlantInstruction);

        CareInstruction treeAndShrubInstruction = new CareInstruction();
        treeAndShrubInstruction.setTagTitle("Bäume und Sträucher");
        treeAndShrubInstruction.setWater("In den ersten Jahren nach der Pflanzung regelmäßig gießen, später nur bei langer Trockenheit.");
        treeAndShrubInstruction.setFertilize("Im Frühjahr mit einem organischen Langzeitdünger düngen.");
        treeAndShrubInstruction.setLocation("Ein Standort mit ausreichend Platz und Licht ist wichtig für ein gesundes Wachstum.");
        careInstructionService.save(treeAndShrubInstruction);

        CareInstruction climbingPlantInstruction = new CareInstruction();
        climbingPlantInstruction.setTagTitle("Kletterpflanzen");
        climbingPlantInstruction.setWater("Kletterpflanzen gleichmäßig feucht halten, aber Staunässe vermeiden.");
        climbingPlantInstruction.setFertilize("Im Frühling und Sommer alle zwei Wochen mit einem stickstoffreichen Dünger versorgen.");
        climbingPlantInstruction.setLocation("Einen sonnigen bis halbschattigen Standort mit einer stabilen Kletterhilfe wählen.");
        careInstructionService.save(climbingPlantInstruction);

        CareInstruction herbInstruction = new CareInstruction();
        herbInstruction.setTagTitle("Kräuter");
        herbInstruction.setWater("Kräuter regelmäßig gießen, aber Staunässe vermeiden, die Erde sollte gut durchlässig sein.");
        herbInstruction.setFertilize("Während der Wachstumsperiode alle vier bis sechs Wochen leicht düngen.");
        herbInstruction.setLocation("Ein sonniger bis halbschattiger Platz fördert das Aroma und das Wachstum.");
        careInstructionService.save(herbInstruction);

        CareInstruction containerPlantInstruction = new CareInstruction();
        containerPlantInstruction.setTagTitle("Kübelpflanzen");
        containerPlantInstruction.setWater("Kübelpflanzen regelmäßig gießen, besonders im Sommer, die Erde sollte gleichmäßig feucht bleiben.");
        containerPlantInstruction.setFertilize("Wöchentlich mit einem flüssigen Kübelpflanzendünger versorgen.");
        containerPlantInstruction.setLocation("Ein heller Standort, der den Bedürfnissen der spezifischen Pflanze entspricht, ist ideal.");
        careInstructionService.save(containerPlantInstruction);

        CareInstruction roseInstruction = new CareInstruction();
        roseInstruction.setTagTitle("Rosen");
        roseInstruction.setWater("Rosen regelmäßig gießen, insbesondere bei Trockenheit, aber nicht über die Blätter.");
        roseInstruction.setFertilize("Im Frühjahr und Sommer mit speziellem Rosendünger alle vier bis sechs Wochen düngen.");
        roseInstruction.setLocation("Ein sonniger und luftiger Platz, der vor starkem Wind geschützt ist, ist ideal.");
        careInstructionService.save(roseInstruction);

        CareInstruction perennialInstruction = new CareInstruction();
        perennialInstruction.setTagTitle("Stauden");
        perennialInstruction.setWater("Stauden regelmäßig gießen, besonders während trockener Perioden im Sommer.");
        perennialInstruction.setFertilize("Im Frühjahr und Sommer mit einem Langzeitdünger versorgen.");
        perennialInstruction.setLocation("Ein sonniger bis halbschattiger Standort, der den speziellen Bedürfnissen der Staude entspricht, ist ideal.");
        careInstructionService.save(perennialInstruction);

        CareInstruction waterPlantInstruction = new CareInstruction();
        waterPlantInstruction.setTagTitle("Wasserpflanzen");
        waterPlantInstruction.setWater("Wasserpflanzen brauchen keine zusätzliche Bewässerung, solange sie im Wasser stehen.");
        waterPlantInstruction.setFertilize("Im Frühjahr mit einem speziellen Wasserpflanzendünger düngen.");
        waterPlantInstruction.setLocation("Ein sonniger bis halbschattiger Platz im Teich oder Wasserbecken ist ideal.");
        careInstructionService.save(waterPlantInstruction);

        CareInstruction ornamentalGrasInstruction = new CareInstruction();
        ornamentalGrasInstruction.setTagTitle("Ziergräser");
        ornamentalGrasInstruction.setWater("Ziergräser regelmäßig gießen, besonders in Trockenperioden.");
        ornamentalGrasInstruction.setFertilize("Im Frühjahr mit einem Langzeitdünger versorgen.");
        ornamentalGrasInstruction.setLocation("Ein sonniger bis halbschattiger Standort fördert das Wachstum und die Farbentwicklung.");
        careInstructionService.save(ornamentalGrasInstruction);

        CareInstruction housePlantInstruction = new CareInstruction();
        housePlantInstruction.setTagTitle("Zimmerpflanzen");
        housePlantInstruction.setWater("Zimmerpflanzen regelmäßig gießen, die Erde leicht feucht halten und Staunässe vermeiden.");
        housePlantInstruction.setFertilize("Alle zwei Wochen mit einem Zimmerpflanzendünger düngen.");
        housePlantInstruction.setLocation("Ein heller Platz ohne direkte Mittagssonne ist ideal für die meisten Zimmerpflanzen.");
        careInstructionService.save(housePlantInstruction);

        CareInstruction onionPlantInstruction = new CareInstruction();
        onionPlantInstruction.setTagTitle("Zwiebel- und Knollenpflanzen");
        onionPlantInstruction.setWater("Während der Wachstumsperiode regelmäßig gießen, die Erde sollte nicht austrocknen.");
        onionPlantInstruction.setFertilize("Im Frühjahr und Sommer mit einem speziellen Zwiebel- und Knollendünger versorgen.");
        onionPlantInstruction.setLocation("Ein sonniger bis halbschattiger Platz fördert die Blüte und das Wachstum.");
        careInstructionService.save(onionPlantInstruction);

        CareInstruction vegetablesInstruction = new CareInstruction();
        vegetablesInstruction.setTagTitle("Gemüse");
        vegetablesInstruction.setWater("Gemüse regelmäßig gießen, die Erde sollte stets gleichmäßig feucht bleiben.");
        vegetablesInstruction.setFertilize("Alle zwei Wochen mit einem Gemüsedünger düngen, um das Wachstum zu unterstützen.");
        vegetablesInstruction.setLocation("Ein sonniger Platz mit gut durchlässigem Boden ist ideal.");
        careInstructionService.save(vegetablesInstruction);

        CareInstruction fruitInstruction = new CareInstruction();
        fruitInstruction.setTagTitle("Obst");
        fruitInstruction.setWater("Obstpflanzen regelmäßig gießen, besonders während der Fruchtbildung.");
        fruitInstruction.setFertilize("Im Frühjahr mit einem organischen Dünger versorgen.");
        fruitInstruction.setLocation("Ein sonniger Standort fördert eine reiche Ernte und gesunde Pflanzen.");
        careInstructionService.save(fruitInstruction);
        //*********************************************************************************

        // Test Quizze
        Quiz quizOne = new Quiz();
        quizOne.setQuestionOne("Welcher Teil der Pflanze führt die Photosynthese durch?");
        quizOne.setPossibleAnswersQuestionOne(Arrays.asList("Wurzeln", "Stamm", "Blätter", "Blüten"));
        quizOne.setRightAnswersQuestionOne("Blätter");
        quizOne.setQuestionTwo("Wie nennt man den Prozess, bei dem Pflanzen Wasser über ihre Blätter verlieren?");
        quizOne.setPossibleAnswersQuestionTwo(Arrays.asList("Transpiration", "Atmung", "Photosynthese", "Keimung"));
        quizOne.setRightAnswersQuestionTwo("Transpiration");
        quizOne.setQuestionThree("Welcher Teil der Pflanze ist für die Aufnahme von Wasser und Nährstoffen aus dem Boden verantwortlich?");
        quizOne.setPossibleAnswersQuestionThree(Arrays.asList("Wurzeln", "Stamm", "Blätter", "Blüten"));
        quizOne.setRightAnswersQuestionThree("Wurzeln");
        quizOne.setIsChosen(true);
        quizService.saveQuiz(quizOne);

        Quiz quizTwo = new Quiz();
        quizTwo.setQuestionOne("Welcher Teil der Pflanze enthält Chlorophyll?");
        quizTwo.setPossibleAnswersQuestionOne(Arrays.asList("Wurzeln", "Stamm", "Blätter", "Blüten"));
        quizTwo.setRightAnswersQuestionOne("Blätter");
        quizTwo.setQuestionTwo("Wie nennt man den Vorgang, bei dem Samen auskeimen?");
        quizTwo.setPossibleAnswersQuestionTwo(Arrays.asList("Keimung", "Blüte", "Bestäubung", "Befruchtung"));
        quizTwo.setRightAnswersQuestionTwo("Keimung");
        quizTwo.setQuestionThree("Welcher Teil der Blume produziert Pollen?");
        quizTwo.setPossibleAnswersQuestionThree(Arrays.asList("Staubblätter", "Fruchtknoten", "Blütenblätter", "Narbe"));
        quizTwo.setRightAnswersQuestionThree("Staubblätter");
        quizOne.setIsChosen(false);
        quizService.saveQuiz(quizTwo);

        Quiz quizThree = new Quiz();
        quizThree.setQuestionOne("Welcher Prozess wandelt Kohlendioxid und Wasser in Glukose und Sauerstoff um?");
        quizThree.setPossibleAnswersQuestionOne(Arrays.asList("Photosynthese", "Transpiration", "Atmung", "Fermentation"));
        quizThree.setRightAnswersQuestionOne("Photosynthese");
        quizThree.setQuestionTwo("Welcher Pflanzenteil ist hauptsächlich für den Transport von Wasser und Nährstoffen verantwortlich?");
        quizThree.setPossibleAnswersQuestionTwo(Arrays.asList("Xylem", "Phloem", "Wurzeln", "Blätter"));
        quizThree.setRightAnswersQuestionTwo("Xylem");
        quizThree.setQuestionThree("Welcher Teil der Pflanze wächst in Richtung Licht?");
        quizThree.setPossibleAnswersQuestionThree(Arrays.asList("Blätter", "Stamm", "Wurzeln", "Blüten"));
        quizThree.setRightAnswersQuestionThree("Stamm");
        quizOne.setIsChosen(false);
        quizService.saveQuiz(quizThree);

        Quiz quizFour = new Quiz();
        quizFour.setQuestionOne("Wie nennt man die männlichen Fortpflanzungsorgane von Blütenpflanzen?");
        quizFour.setPossibleAnswersQuestionOne(Arrays.asList("Staubblätter", "Fruchtblätter", "Blütenblätter", "Kelchblätter"));
        quizFour.setRightAnswersQuestionOne("Staubblätter");
        quizFour.setQuestionTwo("Welcher Prozess beschreibt die Umwandlung von Sonnenlicht in chemische Energie?");
        quizFour.setPossibleAnswersQuestionTwo(Arrays.asList("Photosynthese", "Atmung", "Fermentation", "Transpiration"));
        quizFour.setRightAnswersQuestionTwo("Photosynthese");
        quizFour.setQuestionThree("Welcher Teil der Pflanze ist für die Reproduktion verantwortlich?");
        quizFour.setPossibleAnswersQuestionThree(Arrays.asList("Blüten", "Blätter", "Stamm", "Wurzeln"));
        quizFour.setRightAnswersQuestionThree("Blüten");
        quizOne.setIsChosen(false);
        quizService.saveQuiz(quizFour);




    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
