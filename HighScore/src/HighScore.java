public class HighScore {
    String name;
    int score;

    HighScore(){
        name = "Ransom";
        score = 420;
    }
    HighScore(String name, int score){
        this.name=name;
        this.score = score;
    }
    int getScore(){return score;}
    String getName(){ return name; }

    int compare(HighScore person2){
        return score - person2.score;
    }
}
