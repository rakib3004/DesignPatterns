package AnalyticalHierarchyProcessBuilder;


public class CountCriteriaAHP {
    AHPcriteriaWeight ahPcriteriaWeight;
    AHPSubCriteriaProcess ahpSubCriteriaProcess = new AHPSubCriteriaProcess();
    double[] countCounter = new double[4];

    public AHPcriteriaWeight countCriteriaAHPMethods(double criteria, PriorityData[] priorityData, int numberOfBooks) {



        double[][] countCriteriaAHPMatrix = new double[4][4];
        countCounter = ahpSubCriteriaProcess.countCriteriaCalculationMethods(priorityData, numberOfBooks);
        int iterator, jterator;
        for (iterator = 0; iterator < 4; iterator++) {
            for (jterator = iterator + 1; jterator < 4; jterator++) {
                countCriteriaAHPMatrix[iterator][jterator] = Math.abs(countCounter[iterator] / countCounter[jterator]);
            }
        }



        for (iterator = 0; iterator < 4; iterator++) {
            for (jterator = iterator + 1; jterator < 4; jterator++) {

                countCriteriaAHPMatrix[jterator][iterator] = Math.pow(countCriteriaAHPMatrix[iterator][jterator], -1);
            }
        }
        // set priority 1 for row == column
        for (iterator = 0; iterator < 4; iterator++) {
            countCriteriaAHPMatrix[iterator][iterator] = (1);
        }

        double[] summationMatrix = new double[4];
        for (iterator = 0; iterator < 4; iterator++) {
            for (jterator = 0; jterator < 4; jterator++) {
                summationMatrix[iterator] = summationMatrix[iterator] + countCriteriaAHPMatrix[iterator][jterator];
            }
        }

        for (iterator = 0; iterator < 4; iterator++) {
            for (jterator = 0; jterator < 4; jterator++) {
                countCriteriaAHPMatrix[iterator][jterator] = countCriteriaAHPMatrix[iterator][jterator]
                        / summationMatrix[iterator];
            }
        }
        double[] countWeightMatrix = new double[4];

        for (iterator = 0; iterator < 4; iterator++) {
            for (jterator = 0; jterator < 4; jterator++) {
                countWeightMatrix[iterator] = countWeightMatrix[iterator] + countCriteriaAHPMatrix[jterator][iterator];

            }
            countWeightMatrix[iterator] = countWeightMatrix[iterator] / 4;
        }
        for (iterator = 0; iterator < 4; iterator++) {

            countWeightMatrix[iterator] = countWeightMatrix[iterator] * criteria;
        }

        ahPcriteriaWeight = new AHPcriteriaWeight(countWeightMatrix[0], countWeightMatrix[1], countWeightMatrix[2],
                countWeightMatrix[3]);


        return ahPcriteriaWeight;
    }
}