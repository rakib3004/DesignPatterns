package AHPalgorithm;


public class TypeCriteriaAHP {
    AHPcriteriaWeight ahPcriteriaWeight;
    AHPSubCriteriaProcess ahpSubCriteriaProcess = new AHPSubCriteriaProcess();
    double[] typeCounter = new double[6];

    public AHPcriteriaWeight typeCriteriaAHPMethods(double criteria, PriorityData[] priorityData, int numberOfBooks) {

        double[][] typeCriteriaAHPMatrix = new double[6][6];

        typeCounter = ahpSubCriteriaProcess.typeCriteriaCalculationMethods(priorityData, numberOfBooks);
        int iterator, jterator;

        for (iterator = 0; iterator < 6; iterator++) {
            for (jterator = iterator + 1; jterator < 6; jterator++) {
                typeCriteriaAHPMatrix[iterator][jterator] = typeCounter[iterator] / typeCounter[jterator];
            }
        }


        for (iterator = 0; iterator < 6; iterator++) {
            for (jterator = iterator + 1; jterator < 6; jterator++) {
                typeCriteriaAHPMatrix[jterator][iterator] = Math.pow(typeCriteriaAHPMatrix[iterator][jterator], -1);
            }
        }
        for (iterator = 0; iterator < 6; iterator++) {
            typeCriteriaAHPMatrix[iterator][iterator] = (1);

        }
        double[] summationMatrix = new double[6];

        for (iterator = 0; iterator < 6; iterator++) {
            for (jterator = 0; jterator < 6; jterator++) {
                summationMatrix[iterator] = summationMatrix[iterator] + typeCriteriaAHPMatrix[iterator][jterator];
            }
        }
        for (iterator = 0; iterator < 6; iterator++) {
            for (jterator = 0; jterator < 6; jterator++) {

                typeCriteriaAHPMatrix[iterator][jterator] = typeCriteriaAHPMatrix[iterator][jterator]
                        / summationMatrix[iterator];
            }
        }
        double[] typeWeightMatrix = new double[6];

        for (iterator = 0; iterator < 6; iterator++) {
            for (jterator = 0; jterator < 6; jterator++) {

                typeWeightMatrix[iterator] = typeWeightMatrix[iterator] + typeCriteriaAHPMatrix[jterator][iterator];
            }
            typeWeightMatrix[iterator] = typeWeightMatrix[iterator] / 6;
        }
        for (iterator = 0; iterator < 6; iterator++) {

            typeWeightMatrix[iterator] = typeWeightMatrix[iterator] * criteria;
        }
        ahPcriteriaWeight = new AHPcriteriaWeight(typeWeightMatrix[0],
                typeWeightMatrix[1], typeWeightMatrix[2], typeWeightMatrix[3], typeWeightMatrix[4],
                typeWeightMatrix[5]);



        return ahPcriteriaWeight;

    }
}
