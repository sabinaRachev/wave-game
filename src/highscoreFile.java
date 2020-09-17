import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class highscoreFile {

    private String   fileName;
    private ArrayList<String>scores;

    public highscoreFile(String fileName){
     this.fileName = fileName;
    }

    public ArrayList<String> grabScores(){
        try {
            scores = (ArrayList<String>)Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }catch(Exception grabFileScoreException){
            return null;
        }
        return scores;
    }
    public void saveScores(){
        try {
            Files.write(Paths.get(fileName),scores, Charset.defaultCharset());
        }catch(Exception saveScoreInFileException){
        }
    }
//return index by name
    public int getPlayerIndexInHighscores(String playerName)
    {
        for (int i = 0; i < scores.size(); i++)
            if(scores.get(i).contains(playerName)) return i;
        return -1;
    }
     //return index by score
    public int getPlayerIndexByScore(int score)
    {
        for (int i = 0; i < scores.size(); i++)
            if(getScoreOfPlayerByIndex(i) == score) return i;
        return -1;
    }
    //return score by index
    public int getScoreOfPlayerByIndex(int playerId){
        return   Integer.parseInt(scores.get(playerId).split(":")[1]);
    }
    //return name by index
    public String getPlayerNameByIndex(int playerId){
        return  scores.get(playerId).split(":")[0];
    }
    //find minimal score
    public int findMinIndex(){
        int output=0;
        int minScore=getScoreOfPlayerByIndex(output);
        for (int i=1 ;  i<scores.size(); i++) {
            int currentScore = getScoreOfPlayerByIndex(i);
            if (currentScore < minScore) {
                minScore=currentScore;
                output = i;
            }
        }
        return output;
    }

//sort scores from lowest to highest
    public void getSortedHighscores() {
        scores = grabScores();
            ArrayList<String> sorted = new ArrayList<>();
            ArrayList<Integer> scoreValues = new ArrayList<>();

            for (int i = 0; i < scores.size(); i++)
                scoreValues.add(getScoreOfPlayerByIndex(i));

            Collections.sort(scoreValues);
            for (int i = 0; i < scores.size(); i++)
                sorted.add(i, scores.get(getPlayerIndexByScore(scoreValues.get(i))));
            scores = sorted;

    }
    //update the scores
    public void editHighScore(String name,int newScore){

      scores = grabScores();

      int numScoresLimit = 3;
      int currentNumScores = scores.size();

      if(currentNumScores == 0){
          //no scores in highscores
          scores.add(name+":"+newScore);
      }else {
          int playerId= getPlayerIndexInHighscores(name);
          if (playerId==-1){//if the player name doesnt exists
          if(currentNumScores < numScoresLimit)//less then 3 scores
                scores.add(name+":"+newScore);
              else{
                  int min=findMinIndex();
                  if (getScoreOfPlayerByIndex(min)<newScore)
              scores.set(findMinIndex(),name+":"+newScore);
          }

          }
          else {
              if (getScoreOfPlayerByIndex(playerId)<newScore)
                  scores.set(playerId,name+":"+newScore);
          }
      }
      saveScores();
    }
    public ArrayList<String> getScores(){
        return scores;
    }
}
