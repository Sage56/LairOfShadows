
public class Warrior extends Player {
        protected int Focus;
        
        public Warrior(){
                this.Health=200;
                this.AttackForce=10;
                this.Defense=30;
                this.Focus=100; //place holder
                this.Level=1;
                this.XP=0;
        }
        
        //used to create bot with randomly generated values comparable to our player
        public Warrior(int h, int af, int d, int f)
        {
                this.Health = h;
                this.AttackForce = af;
                this.Defense = d;
                this.Focus = f;
        }
        
        public Warrior(Warrior w){
                Health=w.Health;
                AttackForce=w.AttackForce;
                Defense=w.Defense;
                Focus=w.Focus;
                Level=w.Level;
                XP=w.XP;
        }

        public void adjFocus(int f){this.Focus += f;}
        public void setFocus(int f){this.Focus = f;}
        public int getFocus(){return this.Focus;}
        
        public void PowerStrike(){
                System.out.println("You delievered a critical blow!");
        }
        
        public void Block(){
                System.out.println("You have blocked the enemies attack!");
        }
        
        public void levelUp()
        {
                //If the layer has leveled, increase their attributes by 25%
                int newHealth = this.getHealth();
                newHealth *= 1.25;
                int newDefense = this.getDefense();
                newDefense*=1.25;
                int newAF = this.getAttackForce();
                newAF *=1.25;
                int newFocus = this.getFocus();
                newFocus *= 1.25;
                
                this.setHealth(newHealth);
                this.setDefense(newDefense);
                this.setAttackForce(newAF);
                this.setFocus(newFocus);
        }
        public void warriorFightMenu(Player bot, boolean isBot)
        {
                int damage = 0;
                char actionChoice = ' ';
                System.out.println("Your turn choose an action: ");
                System.out.println("Click X to slice the enemy with your sword!");
                System.out.println("Click Y to run away!");
                System.out.println("Click Z to deliver an especially powerful blow to the enemy (this will cost focus)");
                System.out.println("Click A to block the enemies attack and and lower his defense (this will cost focus)");
                
                if(isBot)
                {
                        int botChoice = Randomizer.randomize(1,100);
                        
                        if(botChoice<=65) actionChoice = 'X';
                        if(botChoice>65 && botChoice<85) actionChoice = 'Z';
                        if(botChoice >85) actionChoice = 'A';
                }
                else
                {
                        /*use the GUI to get the player's actionChoice
                
                 */
                }
                
                switch(actionChoice)
                {
                case 'X':
                        this.basicAttack();
                        damage = (this.getAttackForce() * Randomizer.randomize(50, 100)/100) - (bot.getDefense() * Randomizer.randomize(80, 100)/100);
                        bot.adjHealth(bot.getHealth()-damage);
                        break;
                        
                case 'Y':
                        this.runAway();
                        this.ranAway = true;
                        break;
                
                case 'Z':
                        this.PowerStrike();
                        if(this.Focus>9)
                        {
                                damage = (this.getAttackForce() * Randomizer.randomize(90, 120)/100) - (bot.getDefense() * Randomizer.randomize(80, 100)/100);
                                bot.adjHealth(bot.getHealth()-damage);
                                this.Focus -= 10;
                        }
                        else
                        {
                                System.out.println("You don't have enough Focus for that!"
                                        + "\nThe enemy has stolen your turn!");
                        }//end CritStrike if/else
                        break;
                        
                case 'A':
                        this.Block();
                        if(this.Focus>9)
                        {
                                bot.adjDefense(bot.getDefense() * Randomizer.randomize(5, 20)/100);
                        }
                        else
                        {
                                System.out.println("You don't have enough Focus for that!"
                                        + "\nThe enemy has stolen your turn!");
                        }//end Disarm if/else
                        break;
                        
                        default:
                                System.out.println("Your code is broken at warriorFightMenu()");
                                break;
                }//end switch
                
        }//end warriorFightMenu
}