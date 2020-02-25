package UnionFind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class UnionFinderWeighted 
{
    private int usersId[]; // this.usersId[i] = parents of node i
	private int size[]; // this.size[i] = size of node i
	private int count; // number of connected components

	public UnionFinderWeighted(int N) {
		count = N;
		this.usersId = new int[N];
		this.size = new int[N];
		for (int i = 0; i < N; ++i) {
			this.usersId[i] = i;
			this.size[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	private int root(int p) {
		while (this.usersId[p] != p) {
			this.usersId[p] = this.usersId[this.usersId[p]]; // path compression
			p = this.usersId[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int rootp = root(p);
        int rootq = root(q);
        System.out.println(p + " " + q);
		if (rootp == rootq) return;
		if (this.size[rootp] < this.size[rootq]) {
			this.usersId[rootp] = rootq;
			this.size[rootq] += this.size[rootp];
		} else {
			this.usersId[rootq] = rootp;
			this.size[rootp] += this.size[rootq];
		}
		count--;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
    }
    
    public static void main( String[] args )
    {
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line, datetime;
            int p, q;
            String[] expected;
            
            UnionFinderWeighted uf = new UnionFinderWeighted(
                    Integer.parseInt(reader.readLine())
            );

            line = reader.readLine();
            while (line != null) {
                expected = line.split(" ");
                p = Integer.parseInt(expected[0]);
                q = Integer.parseInt(expected[1]);
                datetime = expected[2] + " " + expected[3];
                 if (!uf.connected(p, q)) {
                    uf.union(p, q);
                }

                line = reader.readLine();
            }
            reader.close();

            uf.print();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        StdIn.isEmpty();
        int n = StdIn.readInt();
        UnionFinderWeighted uf = new UnionFinderWeighted(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        */
    }

    public void print(){
        for(int i = 0; i < this.usersId.length; i++){
            System.out.print(this.usersId[i] + " | ");
        }
        System.out.println();

        for(int i = 0; i < this.size.length; i++){
            System.out.print(this.size[i] + " | ");
        }
    }
}