import java.io.*;
import java.util.*;

public class AprioriDataMining
{
	// Variables globales (Structures de données choisies pour les représentations).

	static Vector<String> candidates = new Vector<String>();
	static List<String> itemSet = new ArrayList<String>();
	static List<String> finalFrequentItemSet = new ArrayList<>();
	static HashMap<String,Integer> frequentItems = new HashMap<String, Integer>();
	static Boolean displayString;

	String newLine = System.getProperty("line.separator");
	int itemsCount,countItemOccurrence=0,afficherNombreItemsetsFrequents=2,displayTransactionNumber=1;

	public static void main(String args[]) throws IOException
	{
		Scanner sc = new Scanner(System.in);

		int noOfTransactions;  // Nombre de lignes de la base.
		double minimumSupport = 1; // supmin.
		Boolean go, progress, valid;
		String test, choix;
		double minimumConfidence = 1;
		String sampleFile = args[0];
		List<String> transactions = new ArrayList<String>();

		String newLine = System.getProperty("line.separator");

		do
		{
			clrscr();

			go = true;
			System.out.println("###############################################################################");
			System.out.println("###### \t\t\t\t\t\t\t\t\t ######\n######\t\t\t\t\t\t\t\t\t ######");
			System.out.println("###### \t\t ************ \t TP INF 2033 \t ************ \t\t ######");
			System.out.println("######\t\t\t\t\t\t\t\t\t ######\n######\t\t\t\t\t\t\t\t\t ######");
			System.out.println("###############################################################################\n");

			System.out.println("\t\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510");
			System.out.println("\t\t Regles d'Association - Supervise : Dr.Thomas Messi Nguele.");
			System.out.println("\t\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518");
			System.out.print("\t\t\t\t\t    \u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\n");
			System.out.println("\t Veuillez choisir une des options:  \u2551      Membre \t     \u2551Matricule");
			System.out.println("\t ---------------------------------  \u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\n\t\t\t\t\t    \u2551\t\t\t     \u2551");
			System.out.println(" \u25A0 1. Extraire Les itemsets frequents.      \u2551 MABOU KAMDEM RANDY     \u2551 19M2104\n___________________________________________ \u2551\t\t\t     \u2551\n\t\t\t\t\t    \u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");
			System.out.println(" \u25A0 2. Extraire les regles d'association.    \u2551 MBASSI ATANGANA BLAISE \u2551 18T2576\n___________________________________________ \u2551\t\t\t     \u2551\n\t\t\t\t\t    \u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");
			System.out.println(" \u25A0 3. Quitter.\t\t\t\t    \u2551 TSAYEM PAGUEM MERVEILLE\u2551 19M2418\n___________________________________________ \u2551\t\t\t     \u2551\n\t\t\t\t\t    \u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");
			System.out.println("\t\t\t\t\t    \u2551 NAKAM YOPDUP MANUELLA  \u2551 19M2233\n\t\t\t\t\t    \u2551\t\t\t     \u2551\n\t\t\t\t\t    \u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550");
			do {
				valid = true;
				System.out.printf("%s", "Choix >> ");
				choix = sc.nextLine();
				if(!(choix.trim()).equals("1") && !(choix.trim()).equals("2") && !(choix.trim()).equals("3"))
					valid = false;
			}while(!valid);

			if(choix.equals("3")) {
				sc.close();
				System.out.println("\n\t \u2591\u2591\u2591\u2591\u2591\u2591 \t Merci d'avoir visite !\n");
				go = false;
			}
			else {

				do {
					clrscr();

					// On vide toutes les structures de données pour chaque nouvelle itération.

					transactions.clear();
					clearData();

					System.out.printf("\n%s", "    \u2588\u2588   \u2588\u2588\u2588   \u2588\u2588   \u2588\u2588\u2588    Personnalise la recherche    \u2588\u2588   \u2588\u2588\u2588   \u2588\u2588   \u2588\u2588\u2588");
					System.out.printf("\n%s", "_______________________________________________________________________________\n\n");

					System.out.printf("            %s\n", "\n          \u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
					System.out.printf("%s\n", "\n            N.B: Ne rien entrer si tout le fichier doit etre analyse. ");
					System.out.printf("            %s\n", "\n          \u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
					System.out.printf("%s", "\nCombien de lignes du DataSet a analyser >> ");
					String nbrLineString = sc.nextLine();
					int numberLine;
					if(nbrLineString == null || nbrLineString.isEmpty())
						numberLine = 2147483647; // Le plus grand entier représentable sur 32 bits pour permettre de parcourir tout le fichier.
					else
						numberLine = Integer.parseInt(nbrLineString);
					System.out.printf("%s", "Entrer le Support Minimum >> ");
					minimumSupport = Double.parseDouble(sc.nextLine());
					if((choix.trim()).equals("2")) {
						System.out.printf("%s", "Entrer la Confiance Minimum (en %) >> ");
						minimumConfidence = Double.parseDouble(sc.nextLine());
						minimumConfidence = minimumConfidence/100;
					}

					BufferedReader file = null;

					// Lecture dans le fichier pour constituer la Base de Données Transactionnelle.

					try {
						file = new BufferedReader(new FileReader(sampleFile));
						file.readLine();
						String line, transaction = "", last = ""; int i = 0;
						String[] element;

						// Parcours du fichier pour remplir la base de données transactionnelle "transactions".

						while ((line = file.readLine()) != null && i < numberLine) {
							element = line.split(";");
							element[2] = (element[2]).trim();
							if((element[2]).equals(""))
								continue;
							if(element[0].equals(last)) {
								transaction = element[2]+";"+transaction;
								i++;
								continue;
							}
							if(last.equals("")) { // Si on est au début du fichier.
								transaction = element[2];
								last = element[0];
								i++;
								continue;
							}
							transactions.add(transaction);
							last = element[0];
							transaction = element[2];
							i++;
						}
						if(i == numberLine)
							transactions.add(transaction);
						numberLine = i;
					}

					catch(IOException e) {
						e.printStackTrace();
					}

					finally {
						try {
							if(file != null)
								file.close();
						}
						catch(IOException exc) {
							exc.printStackTrace();
						}
					}

					noOfTransactions = transactions.size();

					AprioriDataMining a = new AprioriDataMining();

					progress = false;
					switch(choix) {
						case "1":
							clrscr();
							System.out.println("\n\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591 \t ITEMSETS FREQUENTS \t \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\n");
							System.out.println(numberLine+" lignes analysees et les informations suivantes trouvees !\n");
							a.display(noOfTransactions, transactions, minimumSupport, minimumConfidence, false);
							displayString = true;

							// On peut commencer Apriori, les itemsets fréquents sont générés.
							a.aprioriStart(noOfTransactions, transactions, minimumSupport, minimumConfidence);

							break;
						case "2":
							clrscr();
							System.out.println("\n\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591 \t REGLES D'ASSOCIATION \t \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
							System.out.println(numberLine+" lignes analysees et les informations suivantes trouvees !\n");
							a.display(noOfTransactions, transactions, minimumSupport, minimumConfidence, true);
							displayString = false;

							// Génération des règles d'association et affichage avec leur confiance et leur support.
							a.generateAssociationRules(noOfTransactions, transactions, minimumSupport, minimumConfidence);

							break;
					}

					System.out.printf("%s", "\nVoulez-vous analyser plus de donnees ? (O/N) >> ");
					test = sc.nextLine();
					if (test.equals("O") || test.equals("o"))
						progress = true;
				}while(progress);
			}
		}while(go);
		sc.close();
	}
	
	// Chargé de l'affichage des données du main (Nbr de transactions, supmin, confmin, liste items).
	public void display(int noOfTransactions, List<String> transactions, double minimumSupport, double minimumConfidence, Boolean confEnable)
	{
		for(int i = 0; i<noOfTransactions;i++)
		{
			String str = transactions.get(i);
			String[] words = str.split(";");
			int count = words.length;
			for(int j=0;j<count;j++)
			{
				if(i==0)
					itemSet.add(words[j]);
				else
				{ 
					if(!(itemSet.contains(words[j])))
						itemSet.add(words[j]);
				}
			}
		}
		
		itemsCount = itemSet.size(); 
		System.out.println(newLine+"Nombre d'Items = "+itemsCount);
		System.out.println("____________________________________________________________________");
		System.out.println("Nombre de Transactions = "+noOfTransactions); 
		System.out.println("____________________________________________________________________");
		System.out.println("Support Minimum = "+minimumSupport);
		System.out.println("____________________________________________________________________");
		if (confEnable) {
			System.out.println("Confiance Minimum = "+minimumConfidence+" ("+minimumConfidence*100+" %)");
			System.out.println("____________________________________________________________________");
		}
		
		System.out.println("\n    \u00AB   Items presents dans la Base de Donnees (Transactionnelle)   \u00BB"+newLine);
		int nbrDisplayed = 1;
		for(String item : itemSet)
		{
			System.out.println("------> "+item);
			nbrDisplayed++;
			if(nbrDisplayed % 100 == 0) {
				System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
				(new Scanner(System.in)).nextLine();
				System.out.print("\n");
			}
			nbrDisplayed++;
		}
	}
	
	// Affichage des itemsets fréquents de taille 1.
	public void firstFrequentItemSet(int noOfTransactions,List<String> transactions,double minimumSupport, double minimumConfidence)
	{
		int nbrDisplayed = 0;
		System.out.println();
		if(displayString)
			System.out.println("1-Itemset Frequent\n");
		for(int items=0;items<itemSet.size();items++)
		{
			countItemOccurrence=0;
			String itemStr = itemSet.get(items);
			for(int t=0;t<noOfTransactions;t++)
			{
				String transactionStr = transactions.get(t);
				if(transactionStr.contains(itemStr))
				{
					countItemOccurrence++;
				}
			}

			if(countItemOccurrence >= minimumSupport)
			{
				nbrDisplayed++;
				if(nbrDisplayed % 10 == 0 && displayString) {
					System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
					(new Scanner(System.in)).nextLine();
					System.out.println("\n");
				}
				if(displayString)
					System.out.println("{ "+itemStr+" } \n\t\t------------------------------\n\t  *** \t\t\t Support = "+countItemOccurrence+newLine+"_______________________________________________________________________________\n");
				finalFrequentItemSet.add(itemStr);
				frequentItems.put(itemStr, countItemOccurrence);
			}
		}

		if(frequentItems.size() == 0 && displayString)
			System.out.println("(Aucun itemset frequent de taille 1)");
	}
	
	public void aprioriStart(int noOfTransactions,List<String> transactions,double minimumSupport, double minimumConfidence)
	{
		System.out.println("\nCalcul des itemsets frequents...");
		firstFrequentItemSet(noOfTransactions,transactions,minimumSupport,minimumConfidence);
		System.out.println("\nCalcul des frequents de taille 1 termine...");

		int itemsetNumber=1;
		
		// On copie les fréquents de taille 1 dans la liste des candidats.
		for(int i=0;i<finalFrequentItemSet.size();i++)
		{
			String str = finalFrequentItemSet.get(i);
			candidates.add(str);
		} 

		System.out.print("\nCalcul des itemsets frequents de taille superieure...");

		do
		{
			itemsetNumber++;
			
			/* A partir des itemsets de taille i-1, on génère ceux de tailles i (candidats) par jointure (fusion entre eux) à
			la condition qu'ils aient 1 élément qui diffère et on élimine ceux ne respectant pas l'anti-monotonie. */
			
			generateCombinations(itemsetNumber);

			// Parmi ces candidats, on ne garde que les fréquents.

			checkFrequentItems(noOfTransactions,transactions,minimumSupport);
		}
		while(candidates.size()>1);

	}

	// Procédure pour générer les candidats, en passant par des pré-candidats.

	private void generateCombinations(int itr)
	{
		Vector<String> candidatesTemp = new Vector<String>();
		String s1, s2;
		StringTokenizer strToken1, strToken2;
		if(itr == 2)
		{
			for(int i=0; i<candidates.size(); i++)
			{
				strToken1 = new StringTokenizer(candidates.get(i), ";");
				s1 = strToken1.nextToken();
				for(int j=i+1; j<candidates.size(); j++)
				{
					strToken2 = new StringTokenizer(candidates.elementAt(j), ";");
					s2 = strToken2.nextToken();
					String addString = s1+";"+s2;
					candidatesTemp.add(addString);
				}
			}
		}
		else
		{
			for(int i=0; i<candidates.size(); i++)
			{
				for(int j=i+1; j<candidates.size(); j++)
				{
					s1 = new String();
					s2 = new String();
					
					strToken1 = new StringTokenizer(candidates.get(i), ";");
					strToken2 = new StringTokenizer(candidates.get(j), ";");

					for(int s=0; s<itr-2; s++)
					{
						s1 = s1 + ";" + strToken1.nextToken();
						s2 = s2 + ";" + strToken2.nextToken();
					}

					s1 = s1.substring(1, s1.length());
					s2 = s2.substring(1, s2.length());

					if(s2.compareToIgnoreCase(s1)==0)
					{
						String addString = (s1 + ";" + strToken1.nextToken() + ";" + strToken2.nextToken()).trim();
						candidatesTemp.add(addString);
					}
				}
			}
		}
		candidates.clear();
		candidates = new Vector<String>(candidatesTemp);
		candidatesTemp.clear();
	}

	// Procédure permettant de générer les itemsets fréquents à partir des candidats.

	public void checkFrequentItems(int noOfTransactions,List<String> transactions, double minimumSupport)
	{
		String str;
		List<String> combList = new ArrayList<String>();
		for(int i=0;i<candidates.size();i++)
		{
			str = candidates.get(i);
			combList.add(str);
		}
		if(displayString)
			System.out.println(newLine+newLine+afficherNombreItemsetsFrequents+"-Itemset Frequent\n");
		int[] itemSetSupport = new int[combList.size()];

		for(String transac: transactions) // Corps principal d'Apriori.
		{
			int flag = 0; int i = 0;
			for(String comb: combList)
			{
				String[] words = comb.split(";");
				int count = words.length;
				for(int j=0;j<count;j++) { // Vérifie si le candidat se trouve dans la transaction.
					String wordStr = words[j];
					if(transac.contains(wordStr))
					{
						flag++;
					}
				}
				if(flag==count)
				{
					itemSetSupport[i]++; // Si oui, on incrémente le support.
				}
				flag=0;
				i++;
			}
		}
		int nbrFreq = 0;
		for(int i = 0; i<combList.size(); i++) // Parmi ces itemsets on n'affiche que les fréquents (Support >= Supmin).
		{
			str = combList.get(i);
			if(itemSetSupport[i]>=minimumSupport)
			{
				nbrFreq++;
				if(nbrFreq % 6 == 0 && displayString) {
					System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
					(new Scanner(System.in)).nextLine();
					System.out.println("\n");
				}
				if(displayString)
					System.out.println("{ "+str.replaceAll(";", " \u2551 ")+" } \n\t\t------------------------------\n\t  *** \t\t\t Support = "+itemSetSupport[i]+newLine+"_______________________________________________________________________________\n");
				frequentItems.put(str, itemSetSupport[i]);
				finalFrequentItemSet.add(str);
			}
		}
		if(nbrFreq == 0 && displayString)
			System.out.println("(Aucun itemset frequent de taille "+afficherNombreItemsetsFrequents+")");
		afficherNombreItemsetsFrequents++;
	}
	
	public void generateAssociationRules(int noOfTransactions, List<String> transactions, double minimumSupport, double minimumConfidence)
	{
		// L'on doit d'abord générer les fréquents.
		aprioriStart(noOfTransactions,transactions,minimumSupport,minimumConfidence);

		double confidence,confidence1;
		int nbrDisplayed = 1;

		System.out.println(newLine+"\nRegles d'Association extraites"+newLine);

		for(int i=0;i<finalFrequentItemSet.size();i++)
		{
			String itemSetStr = finalFrequentItemSet.get(i);
			double value = frequentItems.get(itemSetStr);
			String str = "",str1 = "";
			String[] words = itemSetStr.split(";");
			int wordCountInString = words.length;
			if(wordCountInString == 2) /* Pour les fréquents de taille (L2) 2*/
			{
				double s = frequentItems.get(words[0]);
				confidence = value/s;
				if(confidence>=minimumConfidence) { // Affichage de words[0] --> words[1]
					System.out.printf("\n********************************************************************************\n%s\n", words[0]+" ------> "+words[1]+" \n\t\t------------------------------\n\t *** \t Confiance = "+ confidence*100+" \t\t Support = "+(int)value+"\n");
					nbrDisplayed++;
					if(nbrDisplayed % 10 == 0) {
						System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
						(new Scanner(System.in)).nextLine();
						System.out.print("\n\n");
					}
				}
				double s1 = frequentItems.get(words[1]);
				confidence = value/s1;
				if(confidence>=minimumConfidence) { // Affichage de words[1] --> words[0]
					System.out.printf("\n********************************************************************************\n%s\n", words[1]+" ------> "+words[0]+" \n\t\t------------------------------\n\t *** \t Confiance = "+ confidence*100+" \t\t Support = "+(int)value+"\n");
					nbrDisplayed++;
					if(nbrDisplayed % 10 == 0) {
						System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
						(new Scanner(System.in)).nextLine();
						System.out.print("\n\n");
					}
				}
			}
			else /* Pour les fréquents de taille > 2 (Lk) avec k > 2 */
			{
				for(int a=0;a<wordCountInString-1;a++)
				{
					if(a == 0)
						str = str+words[a];
					else
						str = str+";"+words[a];
					for(int j=a+1;j<wordCountInString;j++)
						str1 = str1+";"+words[j];

					double s = frequentItems.get(str);
					confidence = value/s;
					str1 = str1.substring(1, str1.length());
					double s1 = frequentItems.get(str1);
					confidence1 = value/s1;

					if(confidence>=minimumConfidence) { // On affiche l'ensemble des règles de partie gauche str et droite str1.
						nbrDisplayed++;
						System.out.printf("\n********************************************************************************\n%s\n", str.replaceAll(";", " \u2551 ")+" ------> "+str1.replaceAll(";", " \u2551 ")+" \n\t\t------------------------------\n\t *** \t Confiance = "+confidence*100+" \t\t Support = "+(int)value+"\n");
						if(nbrDisplayed % 10 == 0) {
							System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
							(new Scanner(System.in)).nextLine();
							System.out.print("\n\n");
						}
					}
					if(confidence1>=minimumConfidence) { // On affiche l'ensemble des règles de partie gauche str1 et droite str.
						nbrDisplayed++;
						System.out.printf("\n********************************************************************************\n%s\n", str1.replaceAll(";", " \u2551 ")+" ------> "+str.replaceAll(";", " \u2551 ")+" \n\t\t------------------------------\n\t *** \t Confiance = "+confidence1*100+" \t\t Support = "+(int)value+"\n");
						if(nbrDisplayed % 10 == 0) {
							System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
							(new Scanner(System.in)).nextLine();
							System.out.print("\n\n");
						}
					}
					str1="";
				}
				str=""; str1="";
			}
			if(nbrDisplayed % 10 == 0) {
				System.out.printf("\n\n%s", "Appuyez sur entree pour afficher la suite >> ");
				(new Scanner(System.in)).nextLine();
				System.out.print("\n\n");
			}
		}
	}

	public static void clrscr(){ // Pour effacer l'écran.
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {}
	}

	public static void clearData(){
		// Pour effacer le contenu des Structures de données utilisées.
			candidates.clear();
			frequentItems.clear();
			itemSet.clear();
			finalFrequentItemSet.clear();
	}
}