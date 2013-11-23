public class Mage extends Player {
        protected int Mana;
        
        public Mage(){
                this.Health=105;
                this.AttackForce=10;
                this.Defense=10;
                this.Mana=200; //place holder
                this.Level=1;
                this.XP=0;
        }
        
        public Mage(Mage m){
                Health=m.Health;
                AttackForce=m.AttackForce;
                Defense=m.Defense;
                Mana=m.Mana;
                Level=m.Level;
                XP=m.XP;
        }
        
        //used to create bot with randomly generated values comparable to our player
        public Mage(int h, int af, int d, int m)
        {
                this.Health = h;
                this.AttackForce = af;
                this.Defense = d;
                this.Mana = m;
        }
        
        public void adjMana(int m){this.Mana += m;}
        public void setMana(int m){this.Mana = m;}
        public int getMana()
        {
                return this.Mana;
        }
        
        public void Cast(){
                System.out.println("You have cast a Spell!");
        }
        
        public void Heal(){
                System.out.println("You have healed yourself!");
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
                int newMana = this.getMana();
                newMana *= 1.25;
                
                this.setHealth(newHealth);
                this.setDefense(newDefense);
                this.setAttackForce(newAF);
                this.setMana(newMana);
        }
        
        public void mageFightMenu(Player bot, boolean isBot)
        {
                int damage = 0;
                char actionChoice = ' ';
                System.out.println("Your turn choose an action: ");
                System.out.println("Click X to slice the enemy with your sword!");
                System.out.println("Click Y to run away!");
                System.out.println("Click Z to cast a bolt of fire at the enemy (this will cost mana)");
                System.out.println("Click A to heal yourself! (this will cost mana)");
                
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
                        this.Cast();
                        if(this.Mana>9)
                        {
                                damage = (this.getAttackForce() * Randomizer.randomize(90, 120)/100) - (bot.getDefense() * Randomizer.randomize(80, 100)/100);
                                bot.adjHealth(bot.getHealth()-damage);
                                this.Mana -= 10;
                        }
                        else
                        {
                                System.out.println("You don't have enough Mana for that!"
                                        + "\nThe enemy has stolen your turn!");
                        }//end Cast if/else
                        break;
                        
                case 'A':
                        this.Heal();
                        if(this.Mana>9)
                        {
                                this.adjHealth(bot.getHealth() * Randomizer.randomize(25, 60)/100);
                        }
                        else
                        {
                                System.out.println("You don't have enough Mana for that!"
                                        + "\nThe enemy has stolen your turn!");
                        }//end Heal if/else
                        break;
                        
                        default:
                                System.out.println("Your code is broken at MageFightMenu()");
                                break;
                }//end switch
        }//end mageFightMenu
}