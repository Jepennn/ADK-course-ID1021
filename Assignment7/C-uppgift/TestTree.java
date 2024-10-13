
public class TestTree {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        tree.add(50);
        tree.add(25);
        tree.add(70);
        tree.add(20);
        tree.add(30);
        tree.add(68);
        tree.add(81);
        tree.add(60);
        tree.add(69);
        tree.add(77);
        tree.add(90);
        tree.add(32);
        tree.add(27);
        tree.add(21);
        tree.add(15);

        tree.breathFirst();
        System.out.println("");

        BinaryTree.Sequence seq = tree.sequence();

        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());

        tree.add(100);
        System.out.println("");

        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());
        System.out.println(seq.next());

    }

}
