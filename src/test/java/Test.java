
import com.glacier.tz.model.Sign;


/**
 * Created by Glacierlx on 2015/12/17.
 */
public class Test {

    public static void main(String[] args) {
        ItemViewModel itemViewModel = new ItemViewModel();
        itemViewModel.name = "hahaha";
        itemViewModel.content = "zouzouzou";

        Test(itemViewModel);
    }

    public static void Test(ViewModel viewModel) {
        System.out.println(viewModel);
    }

    public static class ViewModel {
        public String name;
        public String content;

        public ViewModel() {

        }
        @Override
        public String toString() {
            return "ViewModel{name='" + name + '\'' + ", content='" + content + '\'' + '}';
        }
    }

    public static class ItemViewModel extends ViewModel {

    }

}




