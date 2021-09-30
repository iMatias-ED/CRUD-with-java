package clase_14;

public class HangedGameLogic {
    String randomWords[];
    String userInput[];
    String selectedWord;
    int failedInputs;
    int maxFailedInputs = 6;

    public HangedGameLogic(){
        randomWords = new String[]{
            "Mosaico",
            "Fina",
            "Decoracion",
            "Agente",
            "Detective",
            "Cuadrado",
            "Mechero",
            "Ayuda",
            "Anticongelante",
            "Integral",
            "Secar",
            "Maleta",
            "Acupuntura",
            "Pantalones",
            "Convidar",
            "Boomerang",
            "Levantarse",
            "Manejar",
            "Fruta",
            "Agujeros",
        };
        failedInputs= 0;
    }
    public String getRandomWord(){
        int index = (int) (Math.random()*20);
        selectedWord = randomWords[index];
        selectedWord = selectedWord.toUpperCase();

        return selectedWord;
    }
    public String getInitialLabelContent(){
        failedInputs = 0;
        String labelContent = "";
        for (int i=0; i<selectedWord.length(); i++){
            labelContent += "-";
        }
        return labelContent;
    }
    public int checkInput(String input){
        Character letter = input.toUpperCase().charAt(0);
        if (selectedWord.indexOf(letter)== -1){
            failedInputs += 1;
        }
        return failedInputs;
    }
    public String getLabelContent(String input, String currentText){
        Character letter = input.toUpperCase().charAt(0);
        System.out.println("La palabra seleccionada es "+ selectedWord);

        String result = "";
        
        for (int i=0; i<selectedWord.length(); i++){
            if (currentText.charAt(i)!='-'){
                result+= currentText.charAt(i);
            }
            else if (letter.equals(selectedWord.charAt(i))){
                result+= letter;
            }
            else{
                result += "-";
            }

        }
        if (result.equals(selectedWord)){
            return "#PLAYER-VICTORY#";
        }
        return result;
    }
}
