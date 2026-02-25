// (tbh terrible) example from teacher

void main() {
    String s, neu;
    int position, l;
    char c;

    s = "Drei Chinesen mit dem Kontrabass";
    neu = "";
    position = 0;
    l = s.length();

    while (position < l) {
        c = s.charAt(position);
        if (c == 'e' || c == 'i' || c == 'o') {
            c = 'a';
        }
        neu += c;
        position++;
    }

    System.out.println(neu);
}
