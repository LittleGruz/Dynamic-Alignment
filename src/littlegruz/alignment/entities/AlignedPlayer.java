package littlegruz.alignment.entities;

public class AlignedPlayer{
   private String name;
   private int good;
   private int bad;
   private int rank;
   
   public AlignedPlayer(String name, int positive, int negative, int score){
      this.name = name;
      good = positive;
      bad = negative;
      rank = score;
   }
   public void setName(String name){
      this.name = name;
   }
   public String getName(){
      return name;
   }
   public void setGood(int good){
      this.good = good;
   }
   public int getGood(){
      return good;
   }
   public void setBad(int bad){
      this.bad = bad;
   }
   public int getBad(){
      return bad;
   }
   public void setRank(int rank){
      this.rank = rank;
   }
   public int getRank(){
      return rank;
   }
   public void generateRank(int good, int bad){
      int sum;
      int newGood, newBad;
      newGood = good * 10;
      newBad = bad * 10;
      
      /* Did originally plan to have the players with a negative alignment get
       * increased positive ranking. But then I realised that in most cases,
       * the good deeds would most likely be some sort of deception.
       * Particularly nice players will get increased bad effects to
       * simulate a 'fallen from grace' effect*/
      if(rank == 0){
         sum = newGood - newBad;
         rank += sum;
      }
      else if(newGood / rank > 1.0/32.0){
         sum = newGood - newBad;
         rank += sum;
      }
      else if(newGood / rank > 1.0/64.0){
         sum = (int) (newGood - newBad * 1.41);
         rank += sum;
      }
      else if(newGood / rank > 1.0/96.0){
         sum = newGood - newBad * 2;
         rank += sum;
      }
      else if(newGood / rank > 1.0/128.0){
         sum = (int) (newGood - newBad * 2.83);
         rank += sum;
      }
      else{
         sum = newGood - newBad * 4;
         rank += sum;
      }
   }
}
