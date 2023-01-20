public class PrisonBreakLab {
	private int[] boxes; 

	public PrisonBreakLab(int[] boxes) {
		super();
		this.boxes = boxes;
	}
	
	public PrisonBreakLab() {
		boxes = new int[100];
		reset();
	}
	
	public int howManyPrisoners() {
		return boxes.length; 
	}
	
	public void shuffle() {
		for(int i=0; i<boxes.length; i++) {
			int randomSpot = (int)(Math.random() * boxes.length-1);
			int temp = boxes[i];
			boxes[i] = boxes[randomSpot];
			boxes[randomSpot] = temp; 
		}
	}
	
	public void reset() {
		for (int i=0; i<boxes.length; i++) {
			boxes[i] = i; 
		}
	}
    public boolean simulateSmart(){
        double successfulPathFindsCounter = 0;
        for (int i=0; i<howManyPrisoners(); i++){
            if(followSmartPath(i, false)){
                successfulPathFindsCounter++;
            }
        }

        return successfulPathFindsCounter/howManyPrisoners() == 1; 
    }

	public double simulate(int trails, boolean whichPath){
        double successfulPathFindsCounter = 0;
        if(whichPath){
            for(int i=0; i<trails; i++){
            	if(simulateSmart()){
                	successfulPathFindsCounter++; 
            	}
            	shuffle();
        }
        }
		else{
			for(int i=0; i<trails; i++){
				if(simulateRandom()){
					successfulPathFindsCounter++;
				}
				shuffle();
			}
		}
        

        return (successfulPathFindsCounter/trails) * 100; 
    }
	
	public boolean followSmartPath(int start, boolean show) {
		int temp = start;
		if(show) {
			System.out.print("| Box : " + temp + "|" + " ---> "+ boxes[temp] + " ===> ");
		}
		for(int i=0; i<boxes.length/2; i++) {
			if(boxes[temp] == start) {
				if(show) {
					System.out.print("| Box : " + temp+ " | "+ "---> "+ boxes[temp] + " ===> ");
					System.out.println("true");
				}
				return true;
			}
			else{
				temp = boxes[temp];
			}
			if(show) {
				System.out.print("| Box : "  + temp + " | "+" ---> "+ boxes[temp] + " ===> ");
			}
			
		}
		
		if(show) {
			System.out.println("false");
		}
		return false; 
	}
	public boolean simulateRandom(){
		double successfulPathFindsCounter = 0;
		for(int i=0; i<howManyPrisoners(); i++){
			if(followRandomPath(i, false)){
				successfulPathFindsCounter++;
			}
			
		}

		return successfulPathFindsCounter/howManyPrisoners() == 1; 
	}

	public boolean followRandomPath(int start, boolean show) {
		int randomSpot;
		boolean[] spotsVisted = new boolean[howManyPrisoners()]; 
		
		for(int i=0; i<howManyPrisoners()/2; i++){
			randomSpot = (int)(Math.random()*howManyPrisoners()); 
			while(spotsVisted[randomSpot]){
				randomSpot = (int)(Math.random()*howManyPrisoners());
			}
			
			if(boxes[randomSpot] == start){
				if(show) {
					System.out.print("| Box : " + randomSpot + " | "+ "---> "+ boxes[randomSpot] + " ===> ");
					System.out.println("true");
				}
				return true; 
			}
			else{
				spotsVisted[randomSpot] = true; 
				if(show) {
					System.out.print("| Box : " + randomSpot + " | "  +" ---> "+ boxes[randomSpot] + " ===> ");
				}
				
			}
		}
		if(show) {
			System.out.println("false");
		}
		return false; 
	}
	public String toString() {
		String output = ""; 
		int groups;
		if(boxes.length > 10){
			groups = boxes.length/10;
		}else{
			groups = boxes.length/2; 
		}
		 
		for (int j=0; j<groups; j++){
			for (int i=j; i<boxes.length; i += groups) {
				if(boxes[i]<10 && boxes.length > 10){
					output += "| Box " + i + ": " + boxes[i] + "  | "; 
				}
				else{
					output += "| Box " + i + ": " + boxes[i] + " | "; 
				}
				
			}
			output += "\n";
		}
		
		return output; 
	}

	
	public int[] getBoxes() {
		return boxes;
	}

	public void setBoxes(int[] boxes) {
		this.boxes = boxes;
	}
	
	
	
}