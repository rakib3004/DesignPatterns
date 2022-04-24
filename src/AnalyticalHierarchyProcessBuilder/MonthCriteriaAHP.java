package AnalyticalHierarchyProcessBuilder;


public class MonthCriteriaAHP {
    AHPcriteriaWeight ahPcriteriaWeight;
    AHPSubCriteriaProcess ahpSubCriteriaProcess = new AHPSubCriteriaProcess();
    double[] monthCounter = new double[5];

    public AHPcriteriaWeight monthCriteriaAHPMethods(double criteria, PriorityData[] priorityData, int numberOfBooks) {



        double[][] monthCriterAHPMatrix = new double[5][5];

        monthCounter = ahpSubCriteriaProcess.monthCriteriaCalculationMethods(priorityData, numberOfBooks);
        int iterator, jterator;


        for (iterator = 0; iterator < 5; iterator++) {
            for (jterator = iterator + 1; jterator < 5; jterator++) {
                monthCriterAHPMatrix[iterator][jterator] = (monthCounter[iterator] / monthCounter[jterator]);
            }
        }

        for (iterator = 0; iterator < 5; iterator++) {
            for (jterator = iterator + 1; jterator < 5; jterator++) {
                monthCriterAHPMatrix[jterator][iterator] = Math.pow(monthCriterAHPMatrix[iterator][jterator], -1);

            }
        }
        for (iterator = 0; iterator < 5; iterator++) {

            monthCriterAHPMatrix[iterator][iterator] = (1);
        }
        double[] summationMatrix = new double[5];

        for (iterator = 0; iterator < 5; iterator++) {
            for (jterator = 0; jterator < 5; jterator++) {

                summationMatrix[iterator] = summationMatrix[iterator] + monthCriterAHPMatrix[iterator][jterator];
            }
        }
        for (iterator = 0; iterator < 5; iterator++) {
            for (jterator = 0; jterator < 5; jterator++) {

                monthCriterAHPMatrix[iterator][jterator] = monthCriterAHPMatrix[iterator][jterator]
                        / summationMatrix[iterator];
            }
        }
        double[] monthWeightMatrix = new double[5];

        for (iterator = 0; iterator < 5; iterator++) {
            for (jterator = 0; jterator < 5; jterator++) {
                monthWeightMatrix[iterator] = monthWeightMatrix[iterator] + monthCriterAHPMatrix[jterator][iterator];

            }
            monthWeightMatrix[iterator] = monthWeightMatrix[iterator] / 5;
        }
        for (iterator = 0; iterator < 5; iterator++) {

            monthWeightMatrix[iterator] = monthWeightMatrix[iterator] * criteria;
            ;
        }

        ahPcriteriaWeight = new AHPcriteriaWeight(monthWeightMatrix[0], monthWeightMatrix[1],
                monthWeightMatrix[2], monthWeightMatrix[3], monthWeightMatrix[4]);

        return ahPcriteriaWeight;
    }
}