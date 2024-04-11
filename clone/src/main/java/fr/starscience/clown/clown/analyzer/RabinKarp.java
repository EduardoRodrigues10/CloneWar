package fr.starscience.clown.clown.analyzer;

import java.util.List;

public class RabinKarp {
    /* A transformer en static (mettre dans la BDD au lieu de hashmap)
	private void calculateHash(String bytecode) {
		var resultHash = returnHash(bytecode);
		if (tableHash.containsKey(resultHash)) {
			var listBytecode = tableHash.get(resultHash);
			listBytecode.add(bytecode);
			tableHash.put(resultHash, listBytecode);
		} else {
			var listBytecode = new ArrayList<String>();
			listBytecode.add(bytecode);
			tableHash.put(resultHash, listBytecode);
		}
	}

	public Map<Integer, List<String>> hash(String bytecode) {
		for (var instruction : bytecode.split("\n")) {
			calculateHash(instruction);
		}
		return tableHash;
	}
	*/

    static int hash(String s) {
        int hash = 1;
        var sWithoutBlank = s.strip().toCharArray();
        for (var i = 0; i < sWithoutBlank.length; ++i) {
            if (i == 0) {
                hash = ((sWithoutBlank[i] * 256) % 101) + sWithoutBlank[i + 1];
            } else if (i == sWithoutBlank.length - 1) {
                hash = (hash % 101);
            } else {
                hash = (((hash % 101) * 256) % 101) + sWithoutBlank[i + 1];
            }
        }
        return hash;
    }


    static Integer rabinKarp(String s, String pattern) {
        var hashPattern = hash(pattern);
        for (int i = 0; i <= s.length() - pattern.length(); ++i) {
            var hashS = hash(s.substring(i, i + pattern.length()));
            if (hashS == hashPattern) {
                var equals = true;
                for (int j = 0; j < pattern.length(); ++j) {
                    if (s.charAt(i + j) != pattern.charAt(j)) {
                        equals = false;
                        break;
                    }
                }
                if (equals) {
                    return i;
                }
            }
        }
        return null;
    }

}
