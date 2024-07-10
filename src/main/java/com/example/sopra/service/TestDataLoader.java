package com.example.sopra.service;

import com.example.sopra.entity.*;
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

        // Beispielnutzer
        User hans = new User();
        hans.setUsername("Hans");
        hans.setPassword(passwordEncoder.encode("0987"));
        hans.setName("Hans Bauer");
        hans.setEmail("bauer@exmaple.com");
        hans.setAge(33);
        hans.setGender(Gender.MALE);
        hans.setProfileDescription("aufgewachsen in stuttgart, Sicherheitsingenieur, Naturfreund");
        hans.setProfileImage("profilbild_schafStrecktZunge.jpeg");
        userService.saveUser(hans);

        // Nutzer 1
        User user1 = new User();
        user1.setUsername("AnnaGrün");
        user1.setPassword(passwordEncoder.encode("1234"));
        user1.setName("Anna Müller");
        user1.setEmail("user1@example.com");
        user1.setGender(Gender.EMPTY);
        user1.setSales(7);
        user1.setLevel(15);
        user1.xpToNextLevel = user1.calculateXpToNextLevel();
        user1.setProfileImage("pb1.jpg");
        userService.saveUser(user1);

        // Nutzer 2
        User user2 = new User();
        user2.setUsername("Paul86");
        user2.setPassword(passwordEncoder.encode("5678"));
        user2.setName("Paul K.");
        user2.setEmail("user2@example.com");
        user2.setGender(Gender.EMPTY);
        user2.setSales(8);
        user2.setBuys(4);
        user2.setLevel(10);
        user2.xpToNextLevel = user2.calculateXpToNextLevel();
        userService.saveUser(user2);

        // Nutzer 3
        User user3 = new User();
        user3.setUsername("GrünerDaumen123");
        user3.setPassword(passwordEncoder.encode("abcd"));
        user3.setName("Tom");
        user3.setEmail("user3@example.com");
        user3.setGender(Gender.EMPTY);
        user3.setSales(12);
        user3.setLevel(33);
        user3.xpToNextLevel = user3.calculateXpToNextLevel();
        user3.setProfileImage("pb2.jpg");
        userService.saveUser(user3);

        // Nutzer 4
        User user4 = new User();
        user4.setUsername("FelixDerFlorist");
        user4.setPassword(passwordEncoder.encode("efgh"));
        user4.setName("Felix Felicis");
        user4.setEmail("user4@example.com");
        user4.setGender(Gender.EMPTY);
        user4.setSales(1);
        user4.setBuys(2);
        user4.setLevel(5);
        user4.xpToNextLevel = user4.calculateXpToNextLevel();
        userService.saveUser(user4);

        // Nutzer 5
        User user5 = new User();
        user5.setUsername("Someone");
        user5.setPassword(passwordEncoder.encode("ijkl"));
        user5.setName("A. L.");
        user5.setEmail("user5@example.com");
        user5.setGender(Gender.EMPTY);
        userService.saveUser(user5);


        //Beispielpflanzen ***********************************************************************+
        // Beispielpflanze: Rose

        Plant rose = new Plant();
        rose.setTitle("Rote Rose");
        rose.setPhotos(Arrays.asList("rose2.jpg"));
        rose.setHeight(50);
        rose.setPrice(15.99);
        rose.setHasPlanter(true);
        rose.setDescription("Eine wunderschöne rote Rose.");
        rose.setPotCircumference(20.0);
        rose.setPlantCircumference(30.0);
        rose.setTags(Arrays.asList("Sommerblumen", "Rosen", "Garten"));
        rose.setSeller(user1);
        plantService.savePlant(rose);

        // Tulpe
        Plant tulip = new Plant();
        tulip.setTitle("Tulpe");
        tulip.setPhotos(Arrays.asList("tulip.jpg"));
        tulip.setHeight(30);
        tulip.setPrice(8.99);
        tulip.setHasPlanter(false);
        tulip.setDescription("Eine bunte Tulpe, ideal für den Frühlingsgarten.");
        tulip.setPotCircumference(0.0);
        tulip.setPlantCircumference(10.0);
        tulip.setTags(Arrays.asList("Sommerblumen", "Blume", "Fruehling", "Garten"));
        tulip.setSeller(user2);
        plantService.savePlant(tulip);

        // Kaktus
        Plant cactus = new Plant();
        cactus.setTitle("Echinopsis Kaktus");
        cactus.setPhotos(Arrays.asList("kaktus.jpg"));
        cactus.setHeight(40);
        cactus.setPrice(90.49);
        cactus.setHasPlanter(true);
        cactus.setDescription("");
        cactus.setPotCircumference(30.0);
        cactus.setPlantCircumference(20.0);
        cactus.setTags(Arrays.asList("Zimmerpflanzen", "Sommerblumen", "Kuebelpflanzen"));
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

        //Zimmerpflanzen
        Plant monstera = new Plant();
        monstera.setTitle("Monstera Deliciosa");
        monstera.setPhotos(Arrays.asList("monstera.jpg"));
        monstera.setHeight(80);
        monstera.setPrice(29.99);
        monstera.setHasPlanter(false);
        monstera.setDescription("Eine trendige Zimmerpflanze mit auffälligen Blättern.");
        monstera.setPotCircumference(25.0);
        monstera.setPlantCircumference(40.0);
        monstera.setTags(Arrays.asList("Zimmerpflanzen", "Tropisch", "Blattschmuck"));
        monstera.setSeller(user1);
        plantService.savePlant(monstera);

        Plant ficus = new Plant();
        ficus.setTitle("Ficus Benjamina");
        ficus.setPhotos(Arrays.asList("ficus.jpg"));
        ficus.setHeight(100);
        ficus.setPrice(34.99);
        ficus.setHasPlanter(true);
        ficus.setDescription("Ein klassischer Ficus mit eleganten, glänzenden Blättern.");
        ficus.setPotCircumference(30.0);
        ficus.setPlantCircumference(50.0);
        ficus.setTags(Arrays.asList("Zimmerpflanzen", "Ficus", "Bäume"));
        ficus.setSeller(user1);
        plantService.savePlant(ficus);

        Plant pothos = new Plant();
        pothos.setTitle("Pothos");
        pothos.setPhotos(Arrays.asList("epipremnum.jpg"));
        pothos.setHeight(60);
        pothos.setPrice(19.99);
        pothos.setHasPlanter(false);
        pothos.setDescription("Eine pflegeleichte Hängepflanze mit herzförmigen Blättern.");
        pothos.setPotCircumference(20.0);
        pothos.setPlantCircumference(35.0);
        pothos.setTags(Arrays.asList("Zimmerpflanzen", "Rankpflanzen", "Pflegeleicht"));
        pothos.setSeller(user1);
        plantService.savePlant(pothos);

        Plant snakePlant = new Plant();
        snakePlant.setTitle("Bogenhanf");
        snakePlant.setPhotos(Arrays.asList("bogenhanf.jpg"));
        snakePlant.setHeight(70);
        snakePlant.setPrice(24.99);
        snakePlant.setHasPlanter(true);
        snakePlant.setDescription("Eine robuste Pflanze mit schwertförmigen, aufrechten Blättern.");
        snakePlant.setPotCircumference(22.0);
        snakePlant.setPlantCircumference(38.0);
        snakePlant.setTags(Arrays.asList("Zimmerpflanzen", "Sukkulenten", "Pflegeleicht"));
        snakePlant.setSeller(user1);
        plantService.savePlant(snakePlant);

        Plant peaceLily = new Plant();
        peaceLily.setTitle("Einblatt");
        peaceLily.setPhotos(Arrays.asList("einblatt.jpg"));
        peaceLily.setHeight(50);
        peaceLily.setPrice(21.99);
        peaceLily.setHasPlanter(true);
        peaceLily.setDescription("Eine elegante Pflanze mit weißen Blüten und glänzenden Blättern.");
        peaceLily.setPotCircumference(25.0);
        peaceLily.setPlantCircumference(45.0);
        peaceLily.setTags(Arrays.asList("Zimmerpflanzen", "Blütenpflanzen", "Pflegeleicht"));
        peaceLily.setSeller(user1);
        plantService.savePlant(peaceLily);



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
        treeAndShrubInstruction.setTagTitle("Baeume und Straeucher");
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
        herbInstruction.setTagTitle("Kraeuter");
        herbInstruction.setWater("Kräuter regelmäßig gießen, aber Staunässe vermeiden, die Erde sollte gut durchlässig sein.");
        herbInstruction.setFertilize("Während der Wachstumsperiode alle vier bis sechs Wochen leicht düngen.");
        herbInstruction.setLocation("Ein sonniger bis halbschattiger Platz fördert das Aroma und das Wachstum.");
        careInstructionService.save(herbInstruction);

        CareInstruction containerPlantInstruction = new CareInstruction();
        containerPlantInstruction.setTagTitle("Kuebelpflanzen");
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
        ornamentalGrasInstruction.setTagTitle("Ziergraeser");
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
        vegetablesInstruction.setTagTitle("Gemuese");
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

        // Quizze
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

        Quiz quizFive = new Quiz();
        quizFive.setQuestionOne("Wie nennt man den Teil der Pflanze, der sie im Boden verankert?");
        quizFive.setPossibleAnswersQuestionOne(Arrays.asList("Wurzeln", "Stamm", "Blätter", "Blüten"));
        quizFive.setRightAnswersQuestionOne("Wurzeln");
        quizFive.setQuestionTwo("Welche Substanz absorbiert Licht für die Photosynthese?");
        quizFive.setPossibleAnswersQuestionTwo(Arrays.asList("Chlorophyll", "Wasser", "Kohlenstoffdioxid", "Sauerstoff"));
        quizFive.setRightAnswersQuestionTwo("Chlorophyll");
        quizFive.setQuestionThree("Welcher Teil der Pflanze wird oft als Nahrungsmittel verwendet und speichert Stärke?");
        quizFive.setPossibleAnswersQuestionThree(Arrays.asList("Wurzeln", "Blätter", "Blüten", "Früchte"));
        quizFive.setRightAnswersQuestionThree("Wurzeln");
        quizFive.setIsChosen(false);
        quizService.saveQuiz(quizFive);

        Quiz quizSix = new Quiz();
        quizSix.setQuestionOne("Welche Pflanzenteile sind hauptsächlich für die Fortpflanzung verantwortlich?");
        quizSix.setPossibleAnswersQuestionOne(Arrays.asList("Blüten", "Wurzeln", "Blätter", "Stamm"));
        quizSix.setRightAnswersQuestionOne("Blüten");
        quizSix.setQuestionTwo("Welcher Teil der Pflanze unterstützt die Blätter und transportiert Wasser und Nährstoffe?");
        quizSix.setPossibleAnswersQuestionTwo(Arrays.asList("Stamm", "Wurzeln", "Blüten", "Früchte"));
        quizSix.setRightAnswersQuestionTwo("Stamm");
        quizSix.setQuestionThree("Was produzieren Pflanzen durch Photosynthese?");
        quizSix.setPossibleAnswersQuestionThree(Arrays.asList("Glukose und Sauerstoff", "Wasser und Kohlenstoffdioxid", "Proteine und Mineralien", "Vitamine und Fette"));
        quizSix.setRightAnswersQuestionThree("Glukose und Sauerstoff");
        quizSix.setIsChosen(false);
        quizService.saveQuiz(quizSix);

        Quiz quizSeven = new Quiz();
        quizSeven.setQuestionOne("Welcher Teil der Pflanze ist für die Herstellung von Samen verantwortlich?");
        quizSeven.setPossibleAnswersQuestionOne(Arrays.asList("Blüten", "Blätter", "Stamm", "Wurzeln"));
        quizSeven.setRightAnswersQuestionOne("Blüten");
        quizSeven.setQuestionTwo("Welche Art von Blättern verlieren die meisten Pflanzen im Winter?");
        quizSeven.setPossibleAnswersQuestionTwo(Arrays.asList("Laubblätter", "Nadeln", "Blütenblätter", "Fruchtblätter"));
        quizSeven.setRightAnswersQuestionTwo("Laubblätter");
        quizSeven.setQuestionThree("Welcher Teil der Pflanze enthält Samen?");
        quizSeven.setPossibleAnswersQuestionThree(Arrays.asList("Früchte", "Blätter", "Wurzeln", "Stamm"));
        quizSeven.setRightAnswersQuestionThree("Früchte");
        quizSeven.setIsChosen(false);
        quizService.saveQuiz(quizSeven);

        Quiz quizEight = new Quiz();
        quizEight.setQuestionOne("Wie nennt man den Wachstumsprozess von Pflanzen in Richtung der Schwerkraft?");
        quizEight.setPossibleAnswersQuestionOne(Arrays.asList("Gravitropismus", "Phototropismus", "Hydrotropismus", "Thigmotropismus"));
        quizEight.setRightAnswersQuestionOne("Gravitropismus");
        quizEight.setQuestionTwo("Welche Funktion haben die Blütenblätter einer Pflanze?");
        quizEight.setPossibleAnswersQuestionTwo(Arrays.asList("Anlocken von Bestäubern", "Photosynthese", "Wasseraufnahme", "Nährstoffspeicherung"));
        quizEight.setRightAnswersQuestionTwo("Anlocken von Bestäubern");
        quizEight.setQuestionThree("Welcher Teil der Pflanze schützt die Samen und hilft bei ihrer Verbreitung?");
        quizEight.setPossibleAnswersQuestionThree(Arrays.asList("Früchte", "Wurzeln", "Blätter", "Stamm"));
        quizEight.setRightAnswersQuestionThree("Früchte");
        quizEight.setIsChosen(false);
        quizService.saveQuiz(quizEight);

        Quiz quizNine = new Quiz();
        quizNine.setQuestionOne("Wie nennt man den Prozess, bei dem Pflanzen von einer Blüte zur anderen Pollen übertragen?");
        quizNine.setPossibleAnswersQuestionOne(Arrays.asList("Bestäubung", "Befruchtung", "Keimung", "Vermehrung"));
        quizNine.setRightAnswersQuestionOne("Bestäubung");
        quizNine.setQuestionTwo("Welche Pflanzenart bleibt das ganze Jahr über grün?");
        quizNine.setPossibleAnswersQuestionTwo(Arrays.asList("Immergrün", "Laubabwerfend", "Blütenpflanzen", "Gräser"));
        quizNine.setRightAnswersQuestionTwo("Immergrün");
        quizNine.setQuestionThree("Welcher Teil der Pflanze wächst normalerweise unter der Erde?");
        quizNine.setPossibleAnswersQuestionThree(Arrays.asList("Wurzeln", "Stamm", "Blätter", "Blüten"));
        quizNine.setRightAnswersQuestionThree("Wurzeln");
        quizNine.setIsChosen(false);
        quizService.saveQuiz(quizNine);

        Quiz quizTen = new Quiz();
        quizTen.setQuestionOne("Welche Pflanzenart verliert ihre Blätter im Winter?");
        quizTen.setPossibleAnswersQuestionOne(Arrays.asList("Laubbäume", "Nadelbäume", "Immergrün", "Gräser"));
        quizTen.setRightAnswersQuestionOne("Laubbäume");
        quizTen.setQuestionTwo("Wie nennt man die kleinsten, oft farbenfrohen Teile einer Blume, die die Samen schützen?");
        quizTen.setPossibleAnswersQuestionTwo(Arrays.asList("Blütenblätter", "Staubblätter", "Kelchblätter", "Fruchtknoten"));
        quizTen.setRightAnswersQuestionTwo("Blütenblätter");
        quizTen.setQuestionThree("Welcher Teil der Pflanze nimmt Lichtenergie auf?");
        quizTen.setPossibleAnswersQuestionThree(Arrays.asList("Blätter", "Stamm", "Wurzeln", "Blüten"));
        quizTen.setRightAnswersQuestionThree("Blätter");
        quizTen.setIsChosen(false);
        quizService.saveQuiz(quizTen);

        Quiz quizEleven = new Quiz();
        quizEleven.setQuestionOne("Wie nennt man den Prozess, bei dem Pflanzen aus Samen wachsen?");
        quizEleven.setPossibleAnswersQuestionOne(Arrays.asList("Keimung", "Bestäubung", "Befruchtung", "Photosynthese"));
        quizEleven.setRightAnswersQuestionOne("Keimung");
        quizEleven.setQuestionTwo("Welche Pflanzenart bildet Zapfen zur Fortpflanzung?");
        quizEleven.setPossibleAnswersQuestionTwo(Arrays.asList("Nadelbäume", "Laubbäume", "Gräser", "Blütenpflanzen"));
        quizEleven.setRightAnswersQuestionTwo("Nadelbäume");
        quizEleven.setQuestionThree("Welcher Teil der Pflanze ist oft essbar und enthält Samen?");
        quizEleven.setPossibleAnswersQuestionThree(Arrays.asList("Früchte", "Blätter", "Wurzeln", "Stamm"));
        quizEleven.setRightAnswersQuestionThree("Früchte");
        quizEleven.setIsChosen(false);
        quizService.saveQuiz(quizEleven);

        Quiz quizTwelve = new Quiz();
        quizTwelve.setQuestionOne("Welche Pflanzenart hat Blätter, die sich im Winter verfärben und abfallen?");
        quizTwelve.setPossibleAnswersQuestionOne(Arrays.asList("Laubbäume", "Nadelbäume", "Immergrün", "Gräser"));
        quizTwelve.setRightAnswersQuestionOne("Laubbäume");
        quizTwelve.setQuestionTwo("Wie nennt man den Prozess, bei dem Pflanzen CO2 in die Atmosphäre freisetzen?");
        quizTwelve.setPossibleAnswersQuestionTwo(Arrays.asList("Atmung", "Photosynthese", "Transpiration", "Fermentation"));
        quizTwelve.setRightAnswersQuestionTwo("Atmung");
        quizTwelve.setQuestionThree("Welcher Teil der Pflanze wird oft als Gemüse gegessen und enthält viele Vitamine?");
        quizTwelve.setPossibleAnswersQuestionThree(Arrays.asList("Blätter", "Wurzeln", "Blüten", "Stamm"));
        quizTwelve.setRightAnswersQuestionThree("Blätter");
        quizTwelve.setIsChosen(false);
        quizService.saveQuiz(quizTwelve);





    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
