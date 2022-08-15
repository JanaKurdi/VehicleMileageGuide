//CPCS203
//20/09/2020
import java.util.*;//fo Scanner
import java.io.*;//for PrintWriter
public class VehicleMileageGuide {//class name
     
public static void add_types(String vehicle_types[],Scanner input,PrintWriter output){
     //Method that takes a list of types names sperated with a space
   for(int i=0;i<vehicle_types.length;i++){
      vehicle_types[i]=input.next();
      output.print("\t+ "+vehicle_types[i]+"");  
 } 
}
 
public static int indexOfTypes(String type){
    //Method to find the index of vehcile types
  if(type.matches("Hatchback"))
      return 0;
  if(type.matches("Sedan"))
      return 1;
  if(type.matches("SUV"))
      return 2;
  return -1;//not matches to any of vehcile types
 }

 public static int indexOfManu(String type, String manuF){
     //Method to find the index of Manufacturers
  if(indexOfTypes(type)==0){// Manufacturers of Hatchback
      if(manuF.matches("Toyota"))
          return 0;
      if(manuF.matches("KIA"))
          return 1;
      if(manuF.matches("Hyundai"))
          return 2;
      
  }else if(indexOfTypes(type)==1){// Manufacturersof Sedan
      if(manuF.matches("Chevrolet"))
          return 0;
      if(manuF.matches("Nissan"))
          return 1;
   }else if(indexOfTypes(type)==2){// Manufacturers of SUV
      if(manuF.matches("Ford"))
          return 0;
      if(manuF.matches("Jeep"))
          return 1;
   }
  return -1;//not matchees to any of manufacturers
 }
  
public static void add_manufacturers(Scanner input,PrintWriter output,String manufacturers[][]){
  //Method that take a list of vehicles and manufacturers
  String txt=input.next();
  int index=indexOfTypes(txt);//index of vehcile types from method
  output.println("\t ->VehicleType: "+txt);
  for(int i=0;i<manufacturers[index].length;i++){
  manufacturers[index][i]=input.next();
 output.print("\t  + "+ manufacturers[index][i]);
  
    }
  output.println();
    } 

public static void add_vehicles(Scanner input,PrintWriter output,String vehicles_details[][][],double vehicle_Mileage[][][]){
//Method that separated the informations with a space then print it as sceduals
  String type=input.next();//vehcile types
  String manu=input.next();//manufacturers
  int typeNum=input.nextInt();//number of vehciles
  int indexT=indexOfTypes(type);
  int indexManu=indexOfManu(type,manu);
  vehicles_details[indexT][indexManu]=new String[typeNum];
  vehicle_Mileage[indexT][indexManu]=new double[typeNum];
  
  output.println("\t -> VehicleType: "+type);
     output.println("\t -> Manufacturer: "+manu);
     output.println("\t -> Number of vehicles: "+typeNum); 
     output.println("    ----------------------------------------------------------------------------------------");
     output.println("	Model       		Engine Capacity         Year \t\t\tMileage");
    for(int i=0;i<vehicles_details[indexT][indexManu].length;i++){
      String x1=input.next();//read the string from the input file
      double y1=input.nextDouble();//read the double number from the input file
      vehicles_details[indexT][indexManu][i]=x1;
      vehicle_Mileage[indexT][indexManu][i]=y1;
       String x2[]=vehicles_details[indexT][indexManu][i].split("_");
       output.print("	"+x2[0]+"       		"+x2[1]+"      		"+x2[2]);
      output.printf("\t\t\t"+String.format("%.2f",vehicle_Mileage[indexT][indexManu][i]));
      output.println();
    }
    output.println("    ----------------------------------------------------------------------------------------");
  
 }      

public static void print_result(Scanner input,PrintWriter output,String vehicles_details[][][],double vehicle_Mileage[][][]){
 //Method that print the details about the informations and mileage of the vehicles of specific manufacturer in the secific types
 String type=input.next();
 String manu=input.next();
 int indexT=indexOfTypes(type);
 int indexManu=indexOfManu(type,manu);
  double sum=0;
  double best=vehicle_Mileage[indexT][indexManu][0];
  double worst=vehicle_Mileage[indexT][indexManu][0];
 int bestMileage=0;
 int worstMileage=0;
  
    output.println("\t -> VehicleType: "+type);
    output.println("\t -> Manufacturer: "+manu);
    output.println("\t -> Number of vehicles: "+vehicles_details[indexT][indexManu].length); 
    output.println("  -------------------------------------------------------------------------------------------------------");
    output.println("	Model     	Engine Capacity		Year      		KM/Liter	Cost per KM	 ");
    output.println("  -------------------------------------------------------------------------------------------------------");
    
    for(int i=0;i<vehicles_details[indexT][indexManu].length;i++){
        String x2[]=vehicles_details[indexT][indexManu][i].split("_");
     output.print("	"+x2[0]+"     	"+x2[1]+"		        "+x2[2]);
     output.printf("      		"+String.format("%.2f",vehicle_Mileage[indexT][indexManu][i])+"	  	"+String.format("%.2f",1.43/vehicle_Mileage[indexT][indexManu][i])+"\t SAR");
     output.println();
       
     sum+=vehicle_Mileage[indexT][indexManu][i];
     if(best<=vehicle_Mileage[indexT][indexManu][i]){
         best=vehicle_Mileage[indexT][indexManu][i];
         bestMileage=i;
     }
     if(worst>vehicle_Mileage[indexT][indexManu][i]){
         worst=vehicle_Mileage[indexT][indexManu][i];
         worstMileage=i;
     }
         
     
    }
    output.println("\t-------------------------------------------------------------------------------------------------------");
    double average=sum/vehicles_details[indexT][indexManu].length;
    output.println("\t *The average Mileage is "+(String)String.format("%.2f",average));
     String mileage1[]=vehicles_details[indexT][indexManu][bestMileage].split("_");
      String mileage2[]=vehicles_details[indexT][indexManu][worstMileage].split("_");
     output.println("\t *The best vechile is "+mileage1[0]+" "+mileage1[1]+" "+" ("+mileage1[2]+")");
     output.println("\t *The worst vechile is "+mileage2[0]+" "+mileage2[1]+" "+" ("+mileage2[2]+")");
     
 }
 
 public static double find_average_mileage(PrintWriter output,double vehicle_Mileage[][][]){
     //Method that find the average mileage in the all types
     double sum=0;
     int count=0;
     for(int i=0;i<vehicle_Mileage.length;i++){
         for(int j=0;j<vehicle_Mileage[i].length;j++){
             for(int k=0;k<vehicle_Mileage[i][j].length;k++){
             sum+=vehicle_Mileage[i][j][k];
             count++;
         }
     }
     }
     double average=sum/count;
   output.println("\t * The average Mileage for all vehicles in all vehicle types is "+(String)String.format("%.2f",average));
   return average;
 }
 
 public static void find_best_mileage(PrintWriter output,String[]vehicle_types,String manufacturers[][],String vehicles_details[][][],double vehicle_Mileage[][][]){
    //Method that find the best vehicles in all types
     int bestMileage1=0;
     int bestMileage2=0;
     int bestMileage3=0;
     double best=vehicle_Mileage[0][0][0];
      for(int i=0;i<vehicle_Mileage.length;i++){
         for(int j=0;j<vehicle_Mileage[i].length;j++){
             for(int k=0;k<vehicle_Mileage[i][j].length;k++){
               
           if(best<vehicle_Mileage[i][j][k]) {
                   best=vehicle_Mileage[i][j][k];
                   bestMileage1=i;
                   bestMileage2=j;
                   bestMileage3=k;
               } 
             }
         }
      }
    
   output.println("\t * The best vehicle Mileage in all vehicle types is :");
   output.println("\t--------------------------------------------------------------------------------------------------------");
     output.println("\tModel \t\tEngine Capacity \tYear \t\tMileage \tCost per KM");
     output.println("\t--------------------------------------------------------------------------------------------------------");
     String bestdetails[]=vehicles_details[bestMileage1][bestMileage2][bestMileage3].split("_");
     output.println("\t"+bestdetails[0]+"\t\t"+bestdetails[1]+"\t\t\t"+bestdetails[2]+"\t\t"
          +String.format("%.2f",vehicle_Mileage[bestMileage1][bestMileage2][bestMileage3])+"\t\t"+String.format("%.2f",1.43/vehicle_Mileage[bestMileage1][bestMileage2][bestMileage3])+"\t SAR");
     output.println("\t--------------------------------------------------------------------------------------------------------");
     output.println("\tIn Vechicle Type:"+vehicle_types[ bestMileage1]+", manufacturer: "+manufacturers[bestMileage1][bestMileage2]);
     
     
 }
 
 public static void find_worst_mileage(PrintWriter output,String[]vehicle_types,String manufacturers[][],String vehicles_details[][][],double vehicle_Mileage[][][]){
    //Method that find the worst vehicles in the all types
     int worstMileage1=0;
     int worstMileage2=0;
     int worstMileage3=0;
     double worst=vehicle_Mileage[0][0][0];
      for(int i=0;i<vehicle_Mileage.length;i++){
         for(int j=0;j<vehicle_Mileage[i].length;j++){
             for(int k=0;k<vehicle_Mileage[i][j].length;k++){
       
       if(worst>=vehicle_Mileage[i][j][k]) {
                  worst=vehicle_Mileage[i][j][k];
                  worstMileage1=i;
                  worstMileage2=j;
                  worstMileage3=k;
               } 
             }
         }
      }
    output.println("\t * The worst vehicle Mileage in all vehicle types is :");
    output.println("\t--------------------------------------------------------------------------------------------------------");
    output.println("\tModel \t\tEngine Capacity \tYear \t\tMileage \tCost per KM");
    output.println("\t--------------------------------------------------------------------------------------------------------");
    String worstdetails[]=vehicles_details[worstMileage1][worstMileage2][worstMileage3].split("_");
    output.println("\t"+worstdetails[0]+"\t"+worstdetails[1]+"\t\t\t"+worstdetails[2]+"\t\t"+String.format("%.2f",vehicle_Mileage[worstMileage1][worstMileage2][worstMileage3])+"\t\t"+String.format("%.2f",1.43/vehicle_Mileage[worstMileage1][worstMileage2][worstMileage3])+"\t SAR");
    output.println("\t--------------------------------------------------------------------------------------------------------");
    output.println("\tIn Vechicle Type:"+vehicle_types[worstMileage1]+", Manufacturer: "+manufacturers[worstMileage1][worstMileage2]);
     
   
 }
 
 public static void about_guide(PrintWriter output){
     //Method print developer informations
     output.println("\t -> Developed By: Jana Kurdi");
     output.println("\t -> University ID: 1906167");
     output.println("\t -> Section: GAR");
}
        
public static void exit(PrintWriter output){
//Method that end the program and shows the current date and time
    Date currentDate=new Date();
    output.println("Thank You! :)");
    output.print("Report generated on "+currentDate);
    output.flush();
    output.close();
    System.exit(0);
   
} 

public static void main(String[] args) throws FileNotFoundException  {
        // TODO code application logic here
     File file=new File("input.txt");//Create a file instance
     Scanner input=new Scanner(file);//Create Scanner for the file
     if(!file.exists()){//Check if the file exists or not
         System.out.println("File does not exist");
         System.exit(0);
     }
    
 PrintWriter output=new PrintWriter("output.txt");//create file output

 //Declare arrays and read size from input file
 String vehicle_types[]=new String[input.nextInt()];// 1D array for vehicle types
String manufacturers[][]=new String[vehicle_types.length][];//2D array for manufactors
 for(int i=0;i<manufacturers.length;i++){
    manufacturers[i]=new String[input.nextInt()];
 }
String vehicles_details[][][]=new String[vehicle_types.length][][];//3D array for more info about vehcile
for(int i=0;i<vehicles_details.length;i++){
    vehicles_details[i]=new String[manufacturers[i].length][];
}
 double vehicle_Mileage[][][]=new double[vehicle_types.length][][];//3D array for vehcile mileage
 for(int i=0;i<vehicle_Mileage.length;i++){
   vehicle_Mileage[i]=new double [manufacturers[i].length][];
}
 //Write formatted output to the file
 output.println("################################################\n" +
"########      Vehicle Mileage Guide     ########\n" +
"################################################");
   output.println();
  output.println("-Number of vechicle types: "+vehicle_types.length);
  output.println();
  
while(input.hasNext()){
  String command=input.next();
    if(command.matches("add_vehicletypes")){
       output.println("- Command:"+ command); 
       //Command #1 method
        add_types(vehicle_types,input,output);
        output.println();
   
    }else if(command.matches("add_manufacturers")){
         output.println();
         //Command #2 method
    output.println("- Command: "+command);
    add_manufacturers(input,output,manufacturers);
      
      
    }else if(command.matches("add_vehicles")){
     output.println();
     output.println("- Command: "+ command);
     //Command #3 method
     add_vehicles(input,output,vehicles_details,vehicle_Mileage);
    
    } else if(command.matches("print_result")){
         output.println();
        output.println("- Command: "+ command); 
        //Command #4
    print_result(input,output,vehicles_details,vehicle_Mileage);
 
     }else if(command.matches("find_average_mileage")){
        output.println();
        output.println("- Command: "+ command); 
        //Command #5
       find_average_mileage(output,vehicle_Mileage);
  
     } else if(command.matches("find_best_mileage")){
        output.println();
         output.println("- Command: "+ command); 
         //Command #6
        find_best_mileage(output,vehicle_types,manufacturers,vehicles_details,vehicle_Mileage);
  
     }else if(command.matches("find_worst_mileage")){
        output.println();
       output.println("- Command: "+ command); 
       //Command #7
      find_worst_mileage(output,vehicle_types,manufacturers,vehicles_details,vehicle_Mileage);
  
     }else if (command.matches("about_guide")){
         output.println();
         output.println("- Command:"+ command);
         //Command #8 method
         about_guide(output);
   
     } else if(command.matches("exit")){
       output.println();
        input.close();
        //Command #9 method
        exit(output);
   }
     
 }
 input.close();//close th file
 output.flush();
 output.close();//close the file
 }
    }
   
  
