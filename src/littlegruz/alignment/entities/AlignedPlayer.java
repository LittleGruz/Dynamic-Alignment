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
}
