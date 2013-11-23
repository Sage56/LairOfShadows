public class Rogue extends Player{
        protected int Agility;
        
        public Rogue(){
                this.Health=110;
                this.AttackForce=30;
                this.Defense=20;
                this.Agility=100; //place holder
                this.Level=0;
                this.XP=0;
        }
        
        public Rogue(Rogue m){
                Health=m.Health;
                AttackForce=m.AttackForce;
                Defense=m.Defense;
                Agility=m.Agility;
                Level=m.Level;
                XP=m.XP;
        }
        
        public Rogue(int h, int af, int d, int a)
        {
                this.Health = h;
                this.AttackForce = af;
                this.Defense = d;
                this.Agility = a;
        }
        
        public void CritStrike(){
                System.out.println("You have landed a critical strike!");
        }
        
        public void Disarm(){
                System.out.println("You have disarmed your opponent!");
        }
        
        public int getAgility(){return this.Agility;}
        
        public void adjAgility(int amount){this.Agility += amount;}
        
        public void setAgility(int amount){this.Agility = amount;}

        public void levelUp()
        {
                //If the layer has leveled, increase their attributes by 25%
                int newHealth = this.getHealth();
                newHealth *= 1.25;
                int newDefense = this.getDefense();
                newDefense*=1.25;
                int newAF = this.getAttackForce();
                newAF *=1.25;
                int newAgility = this.getAgility();
                newAgility *= 1.25;
                
                this.setHealth(newHealth);
                this.setDefense(newDefense);
                this.setAttackForce(newAF);
                this.setAgility(newAgility);
        }
        
        public void rogueFightMenu(Player bot, boolean isBot)
        {
                int damage = 0;
                char actionChoice = ' ';
                System.out.println("Your turn choose an action: ");
                System.out.println("Click X to shank the enemy with your dagger");
                System.out.println("Click Y to run away");
                System.out.println("Click Z to critically wound the enemy (this will cost agility)");
                System.out.println("Click A to disarm the enemy and lower his defenses (this will cost agility)");
                
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
                        this.CritStrike();
                        if(this.Agility>9)
                        {
                                damage = (this.getAttackForce() * Randomizer.randomize(90, 120)/100) - (bot.getDefense() * Randomizer.randomize(80, 100)/100);
                                bot.adjHealth(bot.getHealth()-damage);
                                this.Agility -= 10;
                        }
                        else
                        {
                                System.out.println("You don't have enough Agility for that!"
                                        + "\nThe enemy has stolen your turn!");
                        }//end CritStrik if/else
                        break;
                        
                case 'A':
                        this.Disarm();
                        if(this.Agility>9)
                        {
                                bot.adjDefense(bot.getDefense() * Randomizer.randomize(5, 20)/100);
                        }
                        else
                        {
                                System.out.println("You don't have enough Agility for that!"
                                        + "\nThe enemy has stolen your turn!");
                        }//end Disarm if/else
                        break;
                        
                        default:
                                System.out.println("Your code is broken at rogueFightMenu()");
                                break;
                }//end switch
                
        }//end rogueFightMenu
}