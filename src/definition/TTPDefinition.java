package definition;

import definition.state.CalendarState;
import definition.state.statecode.Date;
import utils.CalendarConfiguration;
import utils.Distance;
import problem.definition.State;

import java.util.ArrayList;

public class TTPDefinition {

    private final int PENALIZATION = 100000;    //penalizacion de violacion de restricciones
    private double [][] matrixDistance;     //matriz de distancia
    private int cantEquipos;    //numero de equiupos
    private int cantFechas;     //numero de fechas para duelos
    private boolean secondRound;    //Puede ser otra variable del problema
    private int cantVecesLocal;     //Juegos como Local permitidos (Restriccion)
    private int cantVecesVisitante;     //Juegos como Visitante permitidos
    private ArrayList<Integer> teamsIndexes;
    private boolean symmetricSecondRound;
    private boolean inauguralGame;
    private boolean championVsSub;
    private int firstPlace;
    private int secondPlace;
    private boolean occidentVsOrient;
    private int[][] duelMatrix;
    private String calendarId;
    private static TTPDefinition ttpDefinition;
    private ArrayList<ArrayList<Integer>> mutationsConfigurationsList;//list of configurations for the mutations
    private ArrayList<Integer> mutationsIndexes;
    private CalendarConfiguration occidentOrientConfiguration;
    private ArrayList<Integer> restIndexes;
    private Integer numberOfDates;
    private ArrayList<Date> dateToStartList;
    private Date dateToStart;
    private boolean useDateToStart;
    private boolean lss;

    private TTPDefinition(){
        /*this.cantEquipos=16;
        this.cantFechas = (cantEquipos-1);//15
        this.cantVecesLocal=4;
        this.cantVecesVisitante=4;
        this.dobleVuelta = false;
        this.teamsIndexes = createTeamsIndexes(cantEquipos);*/

        //fillMatrixDistance( DataFiles.getSingletonDataFiles().getTeamsPairDistances());
        this.restIndexes = new ArrayList<>();
        this.dateToStartList = new ArrayList<>();
        this.dateToStart = new Date();
        this.useDateToStart = false;

        //NEW 11/03/2022
        this.lss = false;
    }

    public static TTPDefinition getInstance(){
        if(ttpDefinition == null){
            ttpDefinition = new TTPDefinition();

            //DEBUG
            System.out.println("TTPDefinition instance created");

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
        this.mutationsConfigurationsList = new ArrayList<>();
    }

    public int getCantFechas() {
        return cantFechas;
    }

    public TTPDefinition getTtpDefinition() {
        return ttpDefinition;
    }

    public ArrayList<ArrayList<Integer>> getMutationsConfigurationsList() {

        /*//DEBUG
        String m = "mutations: \n";
        for (ArrayList<Integer> r: mutationsConfigurationsList) {
            for (Integer c: r) { m += "" + c.toString();
            }
        }
        System.out.println(m);
*/
        return mutationsConfigurationsList;
    }

    public void setMutationsConfigurationsList(ArrayList<ArrayList<Integer>> mutationsConfigurationsList) {
        this.mutationsConfigurationsList = mutationsConfigurationsList;
    }

    public ArrayList<Integer> getMutationsIndexes() {
        return mutationsIndexes;
    }

    public ArrayList<Date> getDateToStartList() {return dateToStartList;}

    public void setDateToStartList(ArrayList<Date> dateToStartList) {this.dateToStartList = dateToStartList;}

    public void setMutationsIndexes(ArrayList<Integer> mutationsIndexes) {
        this.mutationsIndexes = mutationsIndexes;
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

    //Returns index of Champion relative to a subset of the teams
    public int getFirstPlaceRelativeIndex() {
        return teamsIndexes.indexOf(firstPlace);
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    //Returns index of subchampion relative to a subset of the teams
    public int getSeconfPlaceRelativeIndex() {
        return teamsIndexes.indexOf(secondPlace);
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

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public ArrayList<Integer> getTeamsIndexes() {
        return teamsIndexes;
    }

    public CalendarConfiguration getOccidentOrientConfiguration() {
        return occidentOrientConfiguration;
    }

    public void setOccidentOrientConfiguration(CalendarConfiguration occidentOrientConfiguration) {
        this.occidentOrientConfiguration = occidentOrientConfiguration;
    }

    public boolean isUseDateToStart() {return useDateToStart;}

    public void setUseDateToStart(boolean useDateToStart) {this.useDateToStart = useDateToStart;}

    public Integer getNumberOfDates() {return numberOfDates;}

    public void setNumberOfDates(Integer numberOfDates) {this.numberOfDates = numberOfDates;}

    public Date getDateToStart() {return dateToStart;}

    public void setDateToStart(Date dateToStart) {this.dateToStart = dateToStart;}

    public void setCantFechas(int cantFechas) {
        this.cantFechas = cantFechas;
    }

    public void setLss(boolean lss) { this.lss = lss; }

    public boolean isLss(){ return lss; }

    public ArrayList<ArrayList<Integer>> teamsItinerary(State calendar) {
        ArrayList<ArrayList<Integer>> teamDate = new ArrayList<>();
        CalendarConfiguration configuration = ((CalendarState)calendar).getConfiguration();
        if(configuration == null){
            configuration = new CalendarConfiguration(
                    TTPDefinition.getInstance().getCalendarId(), TTPDefinition.getInstance().getTeamsIndexes(), TTPDefinition.getInstance().isInauguralGame(),
                    TTPDefinition.getInstance().isChampionVsSub(), TTPDefinition.getInstance().getFirstPlace(),
                    TTPDefinition.getInstance().getSecondPlace(),TTPDefinition.getInstance().isSecondRound(), TTPDefinition.getInstance().isSymmetricSecondRound(),
                    TTPDefinition.getInstance().isOccidentVsOrient(), TTPDefinition.getInstance().getCantVecesLocal(), TTPDefinition.getInstance().getCantVecesVisitante(), TTPDefinition.getInstance().getRestIndexes()
                    ,TTPDefinition.getInstance().getDuelMatrix());
        }

        ArrayList<Integer> teamsIndexes = (ArrayList<Integer>) configuration.getTeamsIndexes().clone();

        ArrayList<Integer> row = new ArrayList<>();

        if (!useDateToStart){
            for (int k = 0; k < teamsIndexes.size(); k++) {
                row.add(teamsIndexes.get(k));
            }
        }
        else{
            for (int k = 0; k < teamsIndexes.size(); k++) {
                row.add(-1);
            }

            for (int k = 0; k < dateToStart.getGames().size(); k++) {

                int local = dateToStart.getGames().get(k).get(0);
                int visitor = dateToStart.getGames().get(k).get(1);

                int posLocal = teamsIndexes.indexOf(local);
                int posVisitor = teamsIndexes.indexOf(visitor);

                row.set(posLocal, local);
                row.set(posVisitor, local);

            }
        }
        teamDate.add(row);

        int i=0;

        if(configuration.isInauguralGame()){
            ArrayList<Integer> pivotRow = (ArrayList<Integer>) row.clone();
            int posChampeon = teamsIndexes.indexOf(configuration.getChampion());
            int posSub = teamsIndexes.indexOf(configuration.getSecondPlace());

            if (posChampeon != -1 && posSub != -1) {
                pivotRow.set(posSub, configuration.getChampion());
            } else if (posChampeon == -1 && posSub != -1) {
                pivotRow.set(posSub, configuration.getChampion());
            }
            teamDate.add(pivotRow);

            i=1;
        }

        for (; i < calendar.getCode().size(); i++) {
            row = new ArrayList<>();
            for (int k = 0; k < teamsIndexes.size(); k++) {
                row.add(-1);
            }

            if(!restIndexes.isEmpty()){
                if(restIndexes.contains(i) || (configuration.isSecondRoundCalendar() && configuration.getTeamsIndexes().size()-1 == i)){
                    teamDate.add(getRestList());
                }
            }


            Date date = (Date)calendar.getCode().get(i);

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



    public int penalizeVisitorGames(State calendar){
        int cont = 0;
        ArrayList<Integer> counts = new ArrayList<>();
        State state = calendar.clone();
        ArrayList<ArrayList<Integer>> itinerary = TTPDefinition.getInstance().teamsItinerary(state);
        CalendarConfiguration configuration = ((CalendarState)calendar).getConfiguration();
        int maxVisitorGames = configuration.getMaxVisitorGamesInARow();
        ArrayList<Integer> teamsIndexes = (ArrayList<Integer>) configuration.getTeamsIndexes().clone();

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

    public int penalizeChampionGame(State calendar){

        boolean penalize = true;
        CalendarConfiguration configuration = ((CalendarState)calendar).getConfiguration();
        Date date = (Date) calendar.getCode().get(0);
        int i =0;
        while (i < date.getGames().size() && penalize){
            ArrayList<Integer> duel = date.getGames().get(i);

            if(duel.get(0) == configuration.getChampion() && duel.get(1) == configuration.getSecondPlace()){
                penalize = false;
            }
            else{
                i++;
            }
        }
        return penalize? PENALIZATION: 0;
    }

    public int penalizeInauguralGame(State calendar){

        boolean penalize = true;
        CalendarConfiguration configuration = ((CalendarState)calendar).getConfiguration();
        Date date = (Date) calendar.getCode().get(0);
        if(date.getGames().size() == 1 &&
                date.getGames().get(0).get(0) == configuration.getChampion() &&
                date.getGames().get(0).get(1) == configuration.getSecondPlace())
            penalize = false;

        return penalize? PENALIZATION: 0;
    }

    public int penalizeLocalGames(State calendar){
        int cont = 0;

        //calendario actual
        State state = calendar.clone();

        //matriz que guarda las ultimas ocaciones en que 2 equipos se enfrentaron
        ArrayList<Integer> counts = new ArrayList<>();

        //itinerario, matriz de n x m, donde n es la cantidad de fechas del calendario, y m la cantidad de equipos,
        //siendo matriz[i,j] la sede en la cual el equipo j jugar'a en la fecha i
        ArrayList<ArrayList<Integer>> itinerary = TTPDefinition.getInstance().teamsItinerary(state);


        CalendarConfiguration configuration = ((CalendarState)calendar).getConfiguration();
        int maxHomeGames = configuration.getMaxLocalGamesInARow();
        ArrayList<Integer> teamsIndexes = (ArrayList<Integer>) configuration.getTeamsIndexes().clone();
        for (int i = 0; i < teamsIndexes.size(); i++) {
            counts.add(0);
        }

        //por cada uno de los equipos
        for(int i = 1; i  < itinerary.size() - 1; i++) {
            ArrayList<Integer> row = itinerary.get(i);

            if (!compareArrays(row, configuration.getTeamsIndexes())) {
                //por cada una de las sedes que visitar'a el equipo
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
        }
        return cont;
    }

    //NEW for version 2.8
    public int penalizeCloseGames(State calendar) {

        //DEBUG
        System.out.print("TTP.Definition.penalizeCloseGames()");

        /*
        Este m'etodo cuenta las veces que se viola la restricci'on de distancia minima de juegos entre 2 equipos
         */

        //contador de violaciones de la restriccion
        int count = 0;
        try {
            State state = calendar.clone();


            //itinerario, matriz de n x m, donde n es la cantidad de fechas del calendario, y m la cantidad de equipos,
            //siendo matriz[i,j] la sede en la cual el equipo j jugar'a en la fecha i
            ArrayList<ArrayList<Integer>> itinerary = TTPDefinition.getInstance().teamsItinerary(state);

            //Debug
            System.out.println("\tItinerary: ");
            for (int i = 0; i < itinerary.size(); i++) {
                System.out.println("\t" + itinerary.get(i));
            }

            CalendarConfiguration configuration = ((CalendarState) calendar).getConfiguration();
            int minCloseGames = configuration.getMinCloseGamesInARow();
            ArrayList<Integer> teamsIndexes = (ArrayList<Integer>) configuration.getTeamsIndexes().clone();

            //la lista de los indices de los topes de cada equpipo contra cada esquipo es una matriz cuadrada, donde cada
            // celda contiene el ultimo indice o fechasen que esos 2 equipos se toparon,. La matriz empieza inicializada
            // cada celda con -1. a medida que se recorren los itinerarios se va chequeando para cada juego, si hubo uno
            // anterior en el calendario entre los mismos 2 equipos. En la celda estara el valor -1 si es el primer tope,
            // si no, estara el indice del ultimo encuentro. En este caso se restaran ambos indices.
            // Si la diferencia > minDiferencia, penalizar
            // Luego escribir en la celda el valor de este último indice

            //Tomando el indice real del unltimo equipo, como size.
            int size = 16;

            //matriz que guarda las ultimas ocaciones en que 2 equipos se enfrentaron
            ArrayList<ArrayList<Integer>> listaDeIndicesDeJuegosPorEquipo = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {

                ArrayList<Integer> column = new ArrayList<>(size);
                for (int j = 0; j < size; j++) {
                    column.add(-1);
                }
                listaDeIndicesDeJuegosPorEquipo.add(column);
            }

            //un itinerario por cada equipo
            for (int i = 1; i < itinerary.size() - 1; i++) {
                ArrayList<Integer> row = itinerary.get(i);

                if (!compareArrays(row, configuration.getTeamsIndexes())) {

                    //por cada una de las sedes que visitara el equipo
                    for (int j = 0; j < row.size(); j++) {

                        int destiny = row.get(j);
                        int destinyIndex = teamsIndexes.indexOf(destiny);

                        int originIndex = teamsIndexes.get(j);
                        int lastDate = listaDeIndicesDeJuegosPorEquipo.get(originIndex).get(destinyIndex);
                        if (lastDate != -1) {
                            int dif = i - lastDate;
                            if (dif < minCloseGames)
                                count++;
                        }
                        listaDeIndicesDeJuegosPorEquipo.get(originIndex).set(destinyIndex, i);
                        listaDeIndicesDeJuegosPorEquipo.get(destinyIndex).set(originIndex, i);

                        //Debug
                        //System.out.println("\tLast Visits: ");
                        //for (int k = 0; k < listaDeIndicesDeJuegosPorEquipo.size(); k++){
                        //    System.out.println("\t"+listaDeIndicesDeJuegosPorEquipo.get(k));
                        //}

                    }
                }
            }
            //DEBUG
            System.out.println("\tcontador " + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private boolean compareArrays(ArrayList<Integer> current, ArrayList<Integer> teamIndexes){
        boolean equals = true;

        for (int i: teamIndexes) {
            if(!current.contains(i)){
                equals = false;
                break;
            }
        }
        return equals;
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

        int posChampion =TTPDefinition.getInstance().getFirstPlace();
        int posSecond = TTPDefinition.getInstance().getSecondPlace();
        boolean champion = false;
        if (posChampion != -1) {
            champion = true;
            /*if(TTPDefinition.getInstance().isInauguralGame()) {

                // if(posChampion < posSecond){
                matrix[posChampion][posSecond] = 1;
                matrix[posSecond][posChampion] = 2;
                cantLocalsAndVisitorsPerRow.get(posChampion).set(0, cantLocalsAndVisitorsPerRow.get(posChampion).get(1)+1);
                cantLocalsAndVisitorsPerRow.get(posSecond).set(1, cantLocalsAndVisitorsPerRow.get(posSecond).get(1)+1);


            }
            else{*/

            int metaPosChampion = teamsIndexes.indexOf(posChampion);
            int metaPosSecond = teamsIndexes.indexOf(posSecond);
            matrix[metaPosChampion][metaPosSecond] = 2;
            matrix[metaPosSecond][metaPosChampion] = 1;
            //if(!exist){
            cantLocalsAndVisitorsPerRow.get(metaPosChampion).set(1, cantLocalsAndVisitorsPerRow.get(metaPosChampion).get(1)+1);
            cantLocalsAndVisitorsPerRow.get(metaPosSecond).set(0, cantLocalsAndVisitorsPerRow.get(metaPosSecond).get(0)+1);
            //}

            //}
        }

        int cantMaxLocalOrVisitor = matrix.length / 2;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i != j) {
                    if (matrix[i][j] == 0) {
                        if (cantLocalsAndVisitorsPerRow.get(i).get(0) < cantMaxLocalOrVisitor) {
                            if (champion) {
                                if (cantLocalsAndVisitorsPerRow.get(j).get(1) < cantMaxLocalOrVisitor) {
                                    matrix[i][j] = 1;
                                    matrix[j][i] = 2;
                                    cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)+1);
                                    cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)+1);
                                }
                                else {
                                    if (cantLocalsAndVisitorsPerRow.get(i).get(1) < cantMaxLocalOrVisitor && cantLocalsAndVisitorsPerRow.get(j).get(0) < cantMaxLocalOrVisitor){
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
                                        if(i < 0){
                                            i = 0;
                                        }
                                    }
                                }
                            }
                            else {
                                matrix[i][j] = 1;
                                matrix[j][i] = 2;
                                cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)+1);
                                cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)+1);
                            }
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
        int posLocal = -1;
        int posVisitor = -1;
        for(int i=0; i < cantLocalsAndVisitorsPerRow.size();i++){
            if(cantLocalsAndVisitorsPerRow.get(i).get(1) == 1){
                posLocal = i;
            }
            if(cantLocalsAndVisitorsPerRow.get(i).get(0) == 1){
                posVisitor = i;
            }
        }
        if(posLocal != -1 && posVisitor !=-1){
            matrix[posLocal][posVisitor] = 2;
            matrix[posVisitor][posLocal] = 1;
            cantLocalsAndVisitorsPerRow.get(posChampion).set(1, cantLocalsAndVisitorsPerRow.get(posChampion).get(1)-1);
            cantLocalsAndVisitorsPerRow.get(posSecond).set(0, cantLocalsAndVisitorsPerRow.get(posSecond).get(0)-1);
        }



        return matrix;
    }

    public int [][] readjustSymmetricCalendar(int [][]matrix){
        ArrayList<ArrayList<Integer>> cantLocalsAndVisitorsPerRow = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++){
            ArrayList<Integer> row = new ArrayList<>();
            row.add(0);
            row.add(0);
            cantLocalsAndVisitorsPerRow.add(row);
        }
        int cantMaxLocalOrVisitor = matrix.length / 2;

        if(TTPDefinition.getInstance().isChampionVsSub()){
            int posChampion = TTPDefinition.getInstance().getFirstPlace();
            int posSecond  = TTPDefinition.getInstance().getSecondPlace();
            cantLocalsAndVisitorsPerRow.get(posChampion).set(1, cantLocalsAndVisitorsPerRow.get(posChampion).get(1)+1);
            cantLocalsAndVisitorsPerRow.get(posSecond).set(0, cantLocalsAndVisitorsPerRow.get(posSecond).get(0)+1);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i<j){
                if(matrix[i][j] == 1){
                    if (cantLocalsAndVisitorsPerRow.get(i).get(1) < cantMaxLocalOrVisitor && cantLocalsAndVisitorsPerRow.get(j).get(0) < cantMaxLocalOrVisitor){
                        cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)+1);
                        cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)+1);

                        /*cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)-1);
                        cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)-1);*/
                    }

                    /*cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)-1);
                    cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)-1);*/
                }else if(matrix[i][j] == 2){
                    /*matrix[i][j] = 2;
                    matrix[j][i] = 1;*/
                    if (cantLocalsAndVisitorsPerRow.get(i).get(0) < cantMaxLocalOrVisitor && cantLocalsAndVisitorsPerRow.get(j).get(1) < cantMaxLocalOrVisitor) {
                        cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)+1);
                        cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)+1);

                        /*cantLocalsAndVisitorsPerRow.get(i).set(1, cantLocalsAndVisitorsPerRow.get(i).get(1)-1);
                        cantLocalsAndVisitorsPerRow.get(j).set(0, cantLocalsAndVisitorsPerRow.get(j).get(0)-1);*/
                    }
                    /*cantLocalsAndVisitorsPerRow.get(i).set(0, cantLocalsAndVisitorsPerRow.get(i).get(0)-1);
                    cantLocalsAndVisitorsPerRow.get(j).set(1, cantLocalsAndVisitorsPerRow.get(j).get(1)-1);*/
                }
            }
            }
        }

        int poslocal = -1;
        int posVisitante = -1;
        for (int i = 0; i < cantLocalsAndVisitorsPerRow.size(); i++) {
            if(cantLocalsAndVisitorsPerRow.get(i).get(0) == cantMaxLocalOrVisitor &&
                    cantLocalsAndVisitorsPerRow.get(i).get(1) == cantMaxLocalOrVisitor){
                poslocal=i;
            }
            else if(cantLocalsAndVisitorsPerRow.get(i).get(0) == cantMaxLocalOrVisitor-1 &&
                    cantLocalsAndVisitorsPerRow.get(i).get(1) == cantMaxLocalOrVisitor-1){
                posVisitante=i;
            }
        }

        System.out.println("Antes");
        for(int i =0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        if(poslocal !=-1 && posVisitante !=-1){
            cantLocalsAndVisitorsPerRow.get(poslocal).set(1, cantLocalsAndVisitorsPerRow.get(poslocal).get(1)-1);
            cantLocalsAndVisitorsPerRow.get(posVisitante).set(1, cantLocalsAndVisitorsPerRow.get(posVisitante).get(0)+1);
            /*matrix[poslocal][posVisitante]= 2;
            matrix[posVisitante][poslocal]=1;*/
            /*matrix[TTPDefinition.getInstance().getFirstPlace()][posVisitante] = 2;
            matrix[posVisitante][TTPDefinition.getInstance().getFirstPlace()] = 1;
            matrix[poslocal][TTPDefinition.getInstance().getSecondPlace()] = 1;
            matrix[TTPDefinition.getInstance().getSecondPlace()][poslocal] = 2;*/
        }
        System.out.println("Despues");
        for(int i =0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println(cantLocalsAndVisitorsPerRow);

        return  matrix;
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

    public ArrayList<Integer> getRestIndexes() {
        return restIndexes;
    }

    public void setRestIndexes(ArrayList<Integer> restIndexes) {
        this.restIndexes = restIndexes;
    }

    private  ArrayList<Integer> getRestList(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int k = 0; k < teamsIndexes.size(); k++) {
            list.add(teamsIndexes.get(k));
        }
        return list;
    }
}
