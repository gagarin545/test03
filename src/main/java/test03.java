import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class test03 {
    private static Function<Integer[], Integer> get_max_value = (n) -> {
        int m = 0;
        for (int i = 0; i < n.length; i++) {
            if (n[m] < n[i]) m = i;
        }
        return m;
    };

    private static ArrayList<Integer []> t = new ArrayList<>();

    public static <i> void main(String[] args) {
        Integer[] line = {0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11};
        AtomicBoolean status = new AtomicBoolean(true);
        AtomicInteger count = new AtomicInteger();

        t.add( line);

        while(status.get()) {

            Integer[] finalLine = next_value(t.get(t.size()-1));

            t.forEach((e) -> {
                if(Arrays.equals(e , finalLine)) {
                    for (Integer k : e)
                        System.out.print(k + " ");
                    status.set(false);
                  count.set(t.indexOf(e));
                }
            });
            t.add(finalLine);
        }
        System.out.println( t.size() - count.get() + " " +  t.size() );
    }

    private static Integer[] next_value(Integer[] k) {
        int i, n;
        Integer[] s = new Integer[k.length];
        for( i = 0; i < s.length; i++) s[i] = k[i];
        for (n = get_max_value.apply(s) + 1, i = s[n-1], s[n-1] = 0; i > 0; i--) {
            if (n == s.length) n = 0;
            s[n++]++;
        }
        return s;
    }
}