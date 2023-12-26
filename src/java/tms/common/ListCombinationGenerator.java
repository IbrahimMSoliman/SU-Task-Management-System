package tms.common;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class ListCombinationGenerator<T> {
     public ListCombinationGenerator()
         {
       
         }
    //The list from which the combinations should be enerated
    private List<List<T>> uncombinedList;
    //A list representig the current combination
    private List<Pair> currentCombination = new LinkedList<Pair>();
    //telling if there are more combinations
    private boolean hasMoreCombinations = true;
//Create the object and set up the needed informations
public ListCombinationGenerator(List<List<T>> uncombinedList) {
    this.uncombinedList = uncombinedList;
    //Set up the managment information
    Iterator<List<T>> iter = this.uncombinedList.iterator();
    while(iter.hasNext()){
        List<T> currentSubList = iter.next();
        Pair p = new Pair(1,currentSubList.size());
        currentCombination.add(p);
    }
    
}
//This method does not work recursivly.
//It generates a singel combination at each time it is called.
//This means it is not needed to keep all combinations in the RAM.
public List<T> getNextCombination(){
    
    //remember the current combination and return it later as the result
    //the next combination is generated in the following to see if there are realy more combinations
    //contains the result of this method call
    List<T> result = new LinkedList<T>();
    Iterator<Pair> currentCombinationIterator = this.getCurrentCombination().iterator();
    Iterator<List<T>> uncombinedListIterator = this.getUncombinedList().iterator();
    //fill the result variable according to the current values in currentcombination
    while(currentCombinationIterator.hasNext() && uncombinedListIterator.hasNext()){
        Pair currentPair = currentCombinationIterator.next();
        List<T> currentSublist = uncombinedListIterator.next();
        result.add(currentSublist.get(currentPair.getCurrentValue()-1));
    }
    //In the first step the value which gets added to the number.
    //During the algorithem it gets the current overflow
    int overflow = 1;
    //Go from left to right and behave as if the numbers in the
    //currentCombinationList represent a number. This number
    //has a diffrent base at each position. This base is the
    //max count of the pair. The next combination is obtaind by
    //adding 1 to this number.
    Iterator<Pair> iter = this.getCurrentCombination().iterator();
    while(iter.hasNext()){
        //The current position in the number
        Pair currentPair = iter.next();
       
        
        //check if we get a further overflow by adding the
        //overflow from the previous step or if we are done
        //if there is an overflow reset the current number
        //and remember teh new overflow
       
            //if we have an further overflow
            if((currentPair.getCurrentValue()+overflow)>currentPair.getMaxValue()){
                //calculate the new overflow
                overflow = (currentPair.getCurrentValue()+overflow) - currentPair.getMaxValue();
                //reset the current value
                currentPair.setCurrentValue(1);
                
            }else{
                //if we do net have an further overflow
                currentPair.setCurrentValue(currentPair.getCurrentValue()+1);
                //This is indicating that there is one more element
                overflow=0;
                break;
            }
    }
    //remember if there are more combinations
    if(overflow!=0){
        this.setHasMoreCombinations(false);
    }
    //System.out.println(result+ " hasMoreCombinations: " + this.hasMoreCombinations());
    
    return result;
}
public ArrayList<List<String>> create_combinations(ArrayList<List<String>> main_list){
        //Set up some lists
     /*   List<String> list1 = new ArrayList<String>();
        list1.add(new String("A1"));
        list1.add(new String("A2"));
        list1.add(new String("A3"));
        
        List<String> list2 = new ArrayList<String>();
        list2.add(new String("B1"));
        list2.add(new String("B2"));
        list2.add(new String("B3"));
        
        List<String> list3 = new ArrayList<String>();
        list3.add(new String("C1"));
        list3.add(new String("C2"));
        list3.add(new String("C3"));
        List<List<String>> main_list = new ArrayList<List<String>>();
        main_list.add(list1);
        main_list.add(list2);
        main_list.add(list3);*/
         
        ArrayList<List<String>> return_combination = new ArrayList<List<String>>();
        ListCombinationGenerator<String > lcg = new ListCombinationGenerator(main_list);
            
        while(lcg.hasMoreCombinations())
          {  
            return_combination.add(lcg.getNextCombination());
          //System.out.println("currentTupel: " + currentTupel+ "hasMoreCombinations: "  + lcg.hasMoreCombinations);
          }
         return return_combination;    
    }
    private List<Pair> getCurrentCombination() {
        return currentCombination;
    }
    private void setCurrentCombination(List<Pair> currentCombination) {
        this.currentCombination = currentCombination;
    }
    public boolean hasMoreCombinations() {
        return hasMoreCombinations;
    }
    public void setHasMoreCombinations(boolean hasMoreCombinations) {
        this.hasMoreCombinations = hasMoreCombinations;
    }
    public List<List<T>> getUncombinedList() {
        return uncombinedList;
    }
    public void setUncombinedList(List<List<T>> uncombinedList) {
        this.uncombinedList = uncombinedList;
    }
    private class Pair{
        int currentValue = 1;
        int maxValue = 1;
        public Pair(int currentValue, int maxValue){
            this.currentValue = currentValue;
            this.maxValue=maxValue;
        }
        public int getCurrentValue() {
            return currentValue;
        }
        public void setCurrentValue(int currentValue) {
            this.currentValue = currentValue;
        }
        public int getMaxValue() {
            return maxValue;
        }
        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }
        
    }
}