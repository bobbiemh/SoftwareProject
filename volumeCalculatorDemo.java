public class volumeCalculatorDemo{

        public static void main (String[] args){
                int height = 0;
                int length = 0;
                int width = 0;
                int volume = 0;
                
                for(int i = 0; i < args.length; i++)
                {
                        if(i == 0)
                        {
                                length = Integer.parseInt(args[i]);
                        }
                        else if(i == 1)
                        {
                                width = Integer.parseInt(args[i]);
                        }
                        else if(i == 2)
                        {
                                height = Integer.parseInt(args[i]);
                        }
                        else
                                //do nothing
                        System.out.println(args[i]);
                }
                volume = length * height * width;
                System.out.println("Volume = " + volume);
        }
}
