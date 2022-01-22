// https://leetcode.com/problems/connecting-cities-with-minimum-cost/
class Solution {
    public int minimumCost(int n, int[][] connections) {
        int total = 0;
        // set result set to empty first
        ArrayList<Integer> arrList = new ArrayList<>();
        
        // keep track of added vertexes
        HashSet<Integer> set = new HashSet<>();
        
        // make set for union-find data structures
        int[] id = new int[n+1];
        for(int i = 1; i <= n; i++)
            id[i] = i;
        
        // sort the edge according to the weight, lowest first
        Arrays.sort(connections, (a,b) -> a[2] - b[2]);
        
        // loop through each edge
        for(int i = 0 ; i < connections.length; i++) {
            int[] temp = connections[i];
            int fV = temp[0];
            int sV = temp[1];
            int weight = temp[2];
            // if not same set
            if(id[fV] != id[sV]) {
                total+= weight;
                // add to set
                set.add(fV);
                set.add(sV);
                
                // save check in case id[fV] got change during the loop below
                int check = id[fV];
                // union change id
                for(int x = 1; x <= n; x++) {
                    // id of the first one
                    if(id[x] == check) 
                        // set to id of second vertex because it may got change
                        id[x] = id[sV];
                }      
            }
            // test
            /*
            System.out.println("---");
            for(int z = 1; z <=n; z++) {
                System.out.print(id[z] + " ");
            }
            System.out.println("for " + fV + " " + sV + " " + weight);
            */
            if(arrList.size() == n-1)
                break;
        }
        
        int check = id[1];
        for(int i = 2; i <= n; i++) {
            //System.out.println("Index i " + i + " id " + id[i]);
            if(check != id[i])
                return -1;
        }
        return total;
    }
}
