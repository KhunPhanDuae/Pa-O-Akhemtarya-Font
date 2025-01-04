
    function generateCode() {
      const inputText = document.getElementById("inputText").value;
      const lines = inputText.split("$"); // Separate lines by ";"
      let output = "";

      lines.forEach(line => {
        if (line.trim()) {
          const parts = line.split("@"); // Split by ">"
          if (parts.length === 2) {
            const englishText = parts[0].trim();
            const PaOText = parts[1].trim();
            output += `} else if (englishText === "${englishText}") {\n`;
            output += `    document.getElementById("PaOOutput").value = "${PaOText}";\n`;
          }
        }
      });

      document.getElementById("outputText").value = output;
    }

    function copyToClipboard() {
      const outputText = document.getElementById("outputText");
      outputText.select();
      document.execCommand("copy");
      alert("ကိုဒ် copied to clipboard!");
    }
 