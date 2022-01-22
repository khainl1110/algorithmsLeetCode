class Solution {
    // custom comparator
    // https://www.java2novice.com/java-collections-and-util/treeset/comparator-object/
    class DistComp implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            if(a[2] == b[2]) {
                if(a[1] == b[1])
                    return a[0] - b[0];
                return a[1] - b[1];
            }
            else return a[2] - b[2];
        }
    }
    
    public int minCostConnectPoints(int[][] points) {
        // first need to calculate the manhattan distance between all the points and store them
        TreeSet<int[]> treeSet = new TreeSet<>(new DistComp());
        for(int i = 0; i < points.length; i++) {
            for(int j = i+1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                int dis = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                int[] temp = {i,j, dis};
                //System.out.println("Test " + temp[0] + " " + temp[1] + " " + temp[2]);
                treeSet.add(temp);
            }
        }
        
        // array for union find data
        int[] ids = new int[points.length];
        for(int i = 0; i < points.length; i++) {
            ids[i] = i;
        }
        
        int total = 0;
        int numEdges = 0;
        // loop through all the edges to determine MST        
        Iterator<int[]> temp = treeSet.iterator();
        while(temp.hasNext()) {
            if(numEdges == points.length -1)
                break;
            
            int[] next = temp.next();
            int id1 = next[0];
            int id2 = next[1];
            
            if(ids[id1] != ids[id2]) {
                // add to MST
                // System.out.println("Add " + next[0] + " " + next[1] + " " + next[2]);
                total += next[2];
                numEdges++;
                // need to union find 
                // change items connect with id1 to id2
                int newId = ids[id2];
                int oldId = ids[id1];
                for(int i = 0; i < ids.length; i++) {
                    if(ids[i] == oldId)
                        ids[i] = newId;
                }
            } else {
                // System.out.println("Not Add " + next[0] + " " + next[1] + " " + next[2]);
            }
             //System.out.println(next[0] + " " + next[1] + " " + next[2]);
        }
        return total;
    }
    
    
}
