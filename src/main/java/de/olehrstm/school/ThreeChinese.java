void main(String[] args) {
    String input = args.length > 0
            ? args[0]
            : "Drei Chinesen mit dem Kontrabass";

    String output = input
            .replace('e', 'a')
            .replace('i', 'a')
            .replace('o', 'a');

    System.out.println(output);
}
