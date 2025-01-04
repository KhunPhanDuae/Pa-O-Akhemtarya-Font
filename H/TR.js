 
        function translateToPaO() {
            const englishText = document.getElementById("englishInput").value;
            // Static translation logic
            if (englishText === "Hello") {
                document.getElementById("PaOOutput").value = "ခွဲးဟဝ်ဒျာႏ";
           } else if (englishText === "place") {
    document.getElementById("PaOOutput").value = "ခင်ႏလမ်း";
} else if (englishText === "warm") {
    document.getElementById("PaOOutput").value = "လွံꩻ";
 } else if (englishText === "How are you?") {
                document.getElementById("PaOOutput").value = "အုံဟဝ်နေꩻ?";
            } else {
                document.getElementById("PaOOutput").value = "Translation not available.";
            }
        }
