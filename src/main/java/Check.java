import com.modern.refresh.MixedOrderBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Check {
    public static void main(String[] args) {
        List<String> months = List.of("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December");

        /**
         *  r - > list of Sep, Oct, Nov, Dec
         *  y -> Jan Feb Jul May
         *  e - Jun
         *  t -> Aug
         *  h -> Mar
         */
        final var collect = months.stream()
                .collect(Collectors.groupingBy(m -> m.charAt(m.length() - 1)));
        System.out.println(collect);

    }
}




