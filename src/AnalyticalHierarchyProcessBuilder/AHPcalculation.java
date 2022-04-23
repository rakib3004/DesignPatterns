package AnalyticalHierarchyProcessBuilder;


public class AHPcalculation {
    AHPcriteriaWeight ahPcriteriaWeight;

    AHPcriteriaWeight [] ahpWeight= new AHPcriteriaWeight[4];
    TypeCriteriaAHP typeCriteriaAHP = new TypeCriteriaAHP();
    CountCriteriaAHP countCriteriaAHP = new CountCriteriaAHP();
    MonthCriteriaAHP monthCriteriaAHP = new MonthCriteriaAHP();
    PriceCriteriaAHP priceCriteriaAHP = new PriceCriteriaAHP();

    public AHPcriteriaWeight AHPcalculationMethods(PriorityData[] priorityData, int numberOfBooks) {


        double[][] AHPMatrix = new double[4][4];

        int i, j;

        AHPMatrix[0][1] = 2.0;
        AHPMatrix[0][2] = 3.0;
        AHPMatrix[0][3] = 5.0;
        AHPMatrix[1][2] = 2.0;
        AHPMatrix[1][3] = 4.0;
        AHPMatrix[2][3] = 1.5;

        for (i = 0; i < 4; i++) {
            for (j = i + 1; j < 4; j++) {
                AHPMatrix[i][j] = Math.abs(AHPMatrix[i][j]);
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = i + 1; j < 4; j++) {

                AHPMatrix[j][i] = Math.pow(AHPMatrix[i][j], -1);
            }
        }
        for (i = 0; i < 4; i++) {
            AHPMatrix[i][i] = (1);
        }
        double[] summationMatrix = new double[4];

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {

                summationMatrix[i] = summationMatrix[i] + AHPMatrix[i][j];
            }
        }

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {

                AHPMatrix[i][j] = AHPMatrix[i][j] / summationMatrix[i];
            }
        }
        double[] weightMatrix = new double[4];

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {

                weightMatrix[i] = weightMatrix[i] + AHPMatrix[j][i];

            }
            weightMatrix[i] = weightMatrix[i] / 4;
        }


        ahpWeight[3] = typeCriteriaAHP.typeCriteriaAHPMethods(weightMatrix[0], priorityData, numberOfBooks);
        ahpWeight[1] = countCriteriaAHP.countCriteriaAHPMethods(weightMatrix[1], priorityData, numberOfBooks);
        ahpWeight[2]= monthCriteriaAHP.monthCriteriaAHPMethods(weightMatrix[2], priorityData, numberOfBooks);
        ahpWeight[0] = priceCriteriaAHP.priceCriteriaAHPMethods(weightMatrix[3], priorityData, numberOfBooks);

        ahPcriteriaWeight = new AHPcriteriaWeight(ahpWeight[0].getHighPrice(), ahpWeight[0].getMediumPrice(),
                ahpWeight[0].getLowPrice(), ahpWeight[1].getHighlyDemand(),
                ahpWeight[1].getHighMediumDemand(), ahpWeight[1].getLowMediumDemand(),
                ahpWeight[1].getLowlyDemand(), ahpWeight[2].getLatestBook(),
                ahpWeight[2].getNewlyBook(), ahpWeight[2].getRecentlyOldBook(), ahpWeight[2].getOldBook(),
                ahpWeight[2].oldestBook,
                ahpWeight[3].uponnashType, ahpWeight[3].kobitaType,
                ahpWeight[3].scienceFictionType, ahpWeight[3].scienceFictionType,
                ahpWeight[3].kisorUponnashType, ahpWeight[3].othersType);

        return ahPcriteriaWeight;

    }
}
