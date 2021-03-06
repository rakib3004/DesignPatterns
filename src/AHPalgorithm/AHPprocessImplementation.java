package AHPalgorithm;


public class AHPprocessImplementation {
    AHPcriteriaWeight ahPcriteriaWeight;
    CountCriteriaAHP countCriteriaAHP;

    public PriorityData[] ahpProcessImplementationMethods(AHPcriteriaWeight ahPcriteriaWeight,
            PriorityData[] priorityData, int numberOfBooks) {


        int iterator1;


        for (iterator1 = 0; iterator1 < numberOfBooks; iterator1++) {
            if (priorityData[iterator1].borrowPriority <= 10) {

                priorityData[iterator1].borrowPriority = ahPcriteriaWeight.lowlyDemand;
            } else if (priorityData[iterator1].borrowPriority <= 20) {

                priorityData[iterator1].borrowPriority = ahPcriteriaWeight.lowMediumDemand;
            } else if (priorityData[iterator1].borrowPriority <= 30) {

                priorityData[iterator1].borrowPriority = ahPcriteriaWeight.highMediumDemand;
            } else if (priorityData[iterator1].borrowPriority <= 40) {

                priorityData[iterator1].borrowPriority = ahPcriteriaWeight.highlyDemand;
            }
        }



        for (iterator1 = 0; iterator1 < numberOfBooks; iterator1++) {
            if (priorityData[iterator1].bookId.substring(0, 2).equals("01")) {
                // System.out.println(iterator1 + ":::: Uponnash");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.uponnashType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("05")) {
                // System.out.println(iterator1 + ":::: Rochonaboli");

                priorityData[iterator1].bookPriority = ahPcriteriaWeight.uponnashType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("02")) {
                // System.out.println(iterator1 + ":::: Kobita");

                priorityData[iterator1].bookPriority = ahPcriteriaWeight.kobitaType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("13")) {
                // System.out.println(iterator1 + ":::: Biggan");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.scienceFictionType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("14")) {
                // System.out.println(iterator1 + ":::: Gobeshona");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.scienceFictionType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("06")) {
                // System.out.println(iterator1 + ":::: Science Fiction");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.scienceFictionType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("10")) {
                // System.out.println(iterator1 + ":::: Kisore Uponnash");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.kisorUponnashType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("11")) {
                // System.out.println(iterator1 + ":::: Shishu Shahitto");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.kisorUponnashType;
            } else if (priorityData[iterator1].bookId.substring(0, 2).equals("04")) {
                // System.out.println(iterator1 + ":::: Religion");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.religionType;
            } else {
                // System.out.println(iterator1 + ":::: Othres");
                priorityData[iterator1].bookPriority = ahPcriteriaWeight.othersType;
            }
        }
        try {
            // set AHP Priority according to there price priority

            for (iterator1 = 0; iterator1 < numberOfBooks; iterator1++) {
                if (Integer.parseInt(priorityData[iterator1].bookPrice) <= 180) {
                    // System.out.println(iterator1 + ":::: 180 er niche dam");
                    priorityData[iterator1].pricePriority = ahPcriteriaWeight.lowPrice;
                } else if (Integer.parseInt(priorityData[iterator1].bookPrice) <= 250) {
                    // System.out.println(iterator1 + ":::: 250 er niche dam");
                    priorityData[iterator1].pricePriority = ahPcriteriaWeight.mediumPrice;
                } else {
                    // System.out.println(iterator1 + ":::: Besi dam");
                    priorityData[iterator1].pricePriority = ahPcriteriaWeight.highPrice;
                }
            }
        } catch (Exception exception) {
        }
        // set AHP Priority according to there time priority

        for (iterator1 = 0; iterator1 < numberOfBooks; iterator1++) {
            if (priorityData[iterator1].timePriority <= 4.00) {
                // System.out.println(iterator1 + "::::latest book");
                priorityData[iterator1].timePriority = ahPcriteriaWeight.latestBook;
            } else if (priorityData[iterator1].timePriority <= 6.00) {
                // System.out.println(iterator1 + "::::newly book");
                priorityData[iterator1].timePriority = ahPcriteriaWeight.newlyBook;
            } else if (priorityData[iterator1].timePriority <= 8.00) {
                // System.out.println(iterator1 + "::::recently old book");
                priorityData[iterator1].timePriority = ahPcriteriaWeight.recentlyOldBook;
            } else if (priorityData[iterator1].timePriority <= 10.00) {
                // System.out.println(iterator1 + "::::old book");
                priorityData[iterator1].timePriority = ahPcriteriaWeight.oldBook;
            } else if (priorityData[iterator1].timePriority <= 12.00) {
                // System.out.println(iterator1 + "::::oldest book");
                priorityData[iterator1].timePriority = ahPcriteriaWeight.oldestBook;
            }
        }
        double newPriorityValue;
        for (iterator1 = 0; iterator1 < numberOfBooks; iterator1++) {
            newPriorityValue = priorityData[iterator1].bookPriority +
                    priorityData[iterator1].borrowPriority +
                    priorityData[iterator1].timePriority +
                    priorityData[iterator1].pricePriority;


        }

        return priorityData;
    }
}