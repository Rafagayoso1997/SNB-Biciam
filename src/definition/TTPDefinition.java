package definition;

import definition.state.statecode.Date;
import utils.Distance;
import problem.definition.State;

import java.util.ArrayList;

public class TTPDefinition {

    private final int PENALIZATION = 100000;
    private double [][] matrixDistance;
    private int cantEquipos;
    private int cantFechas;
    private boolean secondRound;//Puede ser otra variable del problema
    private int cantVecesLocal;
    private int cantVecesVisitante;
    private ArrayList<Integer> teamsIndexes;
    private boolean symmetricSecondRound;
    private boolean inauguralGame;
    private boolean championVsSub;
    private int firstPlace;
    private int secondPlace;
    private boolean occidentVsOrient;
    private int[][] duelMatrix;
    private static TTPDefinition ttpDefinition;


    private TTPDefinition(){
        /*this.cantEquipos=16;
        this.cantFechas = (cantEquipos-1);//15
        this.cantVecesLocal=4;
        this.cantVecesVisitante=4;
        this.dobleVuelta = false;
        this.teamsIndexes = createTeamsIndexes(cantEquipos);*/

        //fillMatrixDistance( DataFiles.getSingletonDataFiles().getTeamsPairDistances());
    }

    public static TTPDefinition getInstance(){
        if(ttpDefinition == null){
            ttpDefinition = new TTPDefinition();
        }
        return ttpDefinition;
    }

    public double[][] getMatrixDistance() {
        return matrixDistance;
    }

    public void setMatrixDistance(double[][] matrixDistance) {
        this.matrixDistance = matrixDistance;
    }

    public int getCantEquipos() {
        return cantEquipos;
    }



    public void setTeamIndexes(ArrayList<Integer> teamIndexes){
        this.teamsIndexes = teamIndexes;
        this.cantEquipos = teamIndexes.size();
        this.cantFechas = this.cantEquipos -1;
        this.matrixDistance = Distance.getInstance().getMatrixDistance();
    }

    public int getCantFechas() {
        return cantFechas;
    }

    public TTPDefinition getTtpDefinition() {
        return ttpDefinition;
    }

    public void setTtpDefinition(TTPDefinition ttpDefinition) {
        this.ttpDefinition = ttpDefinition;
    }

    public boolean isSecondRound() {
        return secondRound;
    }

    public void setSecondRound(boolean secondRound) {
        this.secondRound = secondRound;
    }

    public boolean isSymmetricSecondRound() {
        return symmetricSecondRound;
    }

    public void setSymmetricSecondRound(boolean symmetricSecondRound) {
        this.symmetricSecondRound = symmetricSecondRound;
    }

    public boolean isChampionVsSub() {
        return championVsSub;
    }

    public void setChampionVsSub(boolean championVsSub) {
        this.championVsSub = championVsSub;
    }

    public boolean isInauguralGame() {
        return inauguralGame;
    }

    public void setInauguralGame(boolean inauguralGame) {
        this.inauguralGame = inauguralGame;
    }


    public int getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(int firstPlace) {
        this.firstPlace = firstPlace;
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(int secondPlace) {
        this.secondPlace = secondPlace;
    }

    public boolean isOccidentVsOrient() {
        return occidentVsOrient;
    }

    public void setOccidentVsOrient(boolean occidentVsOrient) {
        this.occidentVsOrient = occidentVsOrient;
    }

    public int[][] getDuelMatrix() {
        return duelMatrix;
    }

    public void setDuelMatrix(int[][] duelMatrix) {
        this.duelMatrix = duelMatrix;
    }

    public int getCantVecesLocal() {
        return cantVecesLocal;
    }

    public void setCantVecesLocal(int cantVecesLocal) {
        this.cantVecesLocal = cantVecesLocal;
    }

    public int getCantVecesVisitante() {
        return cantVecesVisitante;
    }

    public void setCantVecesVisitante(int cantVecesVisitante) {
        this.cantVecesVisitante = cantVecesVisitante;
    }


    public ArrayList<Integer> getTeamsIndexes() {
        return teamsIndexes;
    }

    public ArrayList<ArrayList<Integer>> teamsItinerary(State state) {
        ArrayList<ArrayList<Integer>> teamDate = new ArrayList<>();
        ArrayList<Integer> teamsIndexes = new ArrayList<>();

        for (int i = 0; i < ((Date)state.getCode().get(0)).getGames().size(); i++) {
            teamsIndexes.add(((Date)state.getCode().get(0)).getGames().get(i).get(0));
            teamsIndexes.add(((Date)state.getCode().get(0)).getGames().get(i).get(1));
        }
        quickSort(teamsIndexes, 0, teamsIndexes.size()-1);

        ArrayList<Integer> row = new ArrayList<>();

        for (int k = 0; k < teamsIndexes.size(); k++) {
            row.add(teamsIndexes.get(k));
        }

        teamDate.add(row);

        for (int i = 0; i < state.getCode().size(); i++) {
            row = new ArrayList<>();
            for (int k = 0; k < teamsIndexes.size(); k++) {
                row.add(-1);
            }

            Date date = (Date)state.getCode().get(i);

            for (int m = 0; m < date.getGames().size(); m++) {
                int first = date.getGames().get(m).get(0);
                int second = date.getGames().get(m).get(1);
                row.set(teamsIndexes.indexOf(first), first);
                row.set(teamsIndexes.indexOf(second), first);
            }

            teamDate.add(row);
        }

        row = new ArrayList<>(teamsIndexes);
        teamDate.add(row);
        return teamDate;
    }

    private ArrayList<Integer> createTeamsIndexes(int cantEquipos){
        ArrayList<Integer> teamsIndexes = new ArrayList<>();

        for (int i = 0; i < cantEquipos ; i++) {
            teamsIndexes.add(i);
        }
        return teamsIndexes;
    }

    public int penalizeVisitorGames(State state){
        int cont = 0;
        ArrayList<Integer> counts = new ArrayList<>();
        ArrayList<ArrayList<Integer>> itinerary = TTPDefinition.getInstance().teamsItinerary(state);
        int maxVisitorGames = TTPDefinition.getInstance().getCantVecesVisitante();
        ArrayList<Integer> teamsIndexes = TTPDefinition.getInstance().getTeamsIndexes();

        for (int i = 0; i < teamsIndexes.size(); i++) {
            counts.add(0);
        }

        for(int i = 1; i  < itinerary.size() - 1; i++) {
            ArrayList<Integer> row = itinerary.get(i);

            for (int j = 0; j < row.size(); j++) {
                int destiny = row.get(j);
                if(destiny != teamsIndexes.get(j)){
                    counts.set(j, counts.get(j) + 1);
                }
                else{
                    counts.set(j, 0);
                }

                if (counts.get(j) > maxVisitorGames) {
                    cont++;
                    counts.set(j, 0);
                }
            }
        }
        return cont;
    }

    public int penalizeLocalGames(State state){
        int cont = 0;
        ArrayList<Integer> counts = new ArrayList<>();
        ArrayList<ArrayList<Integer>> itinerary = TTPDefinition.getInstance().teamsItinerary(state);
        int maxHomeGames = TTPDefinition.getInstance().getCantVecesLocal();
        ArrayList<Integer> teamsIndexes = TTPDefinition.getInstance().getTeamsIndexes();
        for (int i = 0; i < teamsIndexes.size(); i++) {
            counts.add(0);
        }

        for(int i = 1; i  < itinerary.size() - 1; i++) {
            ArrayList<Integer> row = itinerary.get(i);

            for (int j = 0; j < row.size(); j++) {
                int destiny = row.get(j);

                if(destiny == teamsIndexes.get(j)){
                    counts.set(j, counts.get(j) + 1);
                }
                else{
                    counts.set(j, 0);
                }

                if (counts.get(j) > maxHomeGames) {
                    cont++;
                    counts.set(j, 0);
                }
            }
        }
        return cont;
    }

    public int getPenalization() {
        return PENALIZATION;
    }



    public int[][] symmetricCalendar(int[][] matrix) {

        ArrayList<ArrayList<Integer>> cantLocalsAndVisitorsPerRow = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++){
            ArrayList<Integer> row = new ArrayList<>();
            row.add(0);
            row.add(0);
            cantLocalsAndVisitorsPerRow.add(row);
        }


        int cantMaxLocalOrVisitor = matrix.length / 2;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i != j) {
                    if (matrix[i][j] == 0) {
                        if (cantLocalsAndVisitorsPerRow.get(i).get(0) < cantMaxLocalOrVisitor) {
                            matrix[i][j] = 1;
                            matrix[j][i] = 2;
                            cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)+1);
                            cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)+1);
                        }
                        else {
                            if(cantLocalsAndVisitorsPerRow.get(j).get(0) < cantMaxLocalOrVisitor){
                                matrix[i][j] = 2;
                                matrix[j][i] = 1;
                                cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)+1);
                                cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)+1);
                            }
                            else{
                                boolean goBackPossibility = false;
                                int lastRowModified = -1;
                                while (!goBackPossibility && i >= 0){
                                    while (!goBackPossibility & j > 0){
                                        j--;
                                        if (matrix[i][j] == 1){
                                            if (cantLocalsAndVisitorsPerRow.get(i).get(1) < cantMaxLocalOrVisitor && cantLocalsAndVisitorsPerRow.get(j).get(0) < cantMaxLocalOrVisitor){
                                                matrix[i][j] = 2;
                                                matrix[j][i] = 1;

                                                cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)+1);
                                                cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)+1);

                                                cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)-1);
                                                cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)-1);

                                                goBackPossibility = true;
                                                i = lastRowModified;
                                                j = -1;
                                            }
                                        }
                                        else if(matrix[i][j] == 2){
                                            if (cantLocalsAndVisitorsPerRow.get(i).get(0) < cantMaxLocalOrVisitor && cantLocalsAndVisitorsPerRow.get(j).get(1) < cantMaxLocalOrVisitor){
                                                matrix[i][j] = 1;
                                                matrix[j][i] = 2;

                                                cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)+1);
                                                cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)+1);

                                                cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)-1);
                                                cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)-1);

                                                goBackPossibility = true;
                                                i = lastRowModified;
                                                j = -1;
                                            }
                                        }
                                        if(!goBackPossibility && matrix[i][j] != 0){
                                            if (matrix[i][j] == 1){
                                                cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)-1);
                                                cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)-1);
                                            }
                                            else {
                                                cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)-1);
                                                cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)-1);
                                            }
                                            matrix[i][j] = 0;
                                            matrix[j][i] = 0;
                                            if (i < j){
                                                if(i < lastRowModified){
                                                    lastRowModified = i;
                                                }
                                            }
                                            else{
                                                if(j < lastRowModified){
                                                    lastRowModified = j;
                                                }
                                            }
                                        }
                                    }
                                    if (!goBackPossibility){
                                        i--;
                                        j = matrix.length;
                                    }
                                }
                                i = 0;
                            }
                        }
                    }
                }
            }
        }
        return matrix;
    }

    private void quickSort(ArrayList<Integer> list, int left, int right){
        double pivot = list.get(left);
        int i = left;
        int j = right;
        int aux;

        while (i < j){
            while (list.get(i) <= pivot && i < j){
                i++;
            }
            while (list.get(j) > pivot){
                j--;
            }
            if(i < j){
                aux = list.get(i);
                list.set(i, list.get(j));
                list.set(j, aux);
            }
        }
        aux = list.get(left);
        list.set(left, list.get(j));
        list.set(j, aux);

        if(left < j - 1){
            quickSort(list, left, j-1);
        }
        if (j+1 < right){
            quickSort(list, j+1, right);
        }
    }
}
