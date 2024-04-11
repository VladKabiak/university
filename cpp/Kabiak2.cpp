#include <iostream>
#include <string>
#include <cctype>

bool isDigit(char c) {
    return std::isdigit(static_cast<unsigned char>(c)) != 0;
}

bool isAlpha(char c) {
    return std::isalpha(static_cast<unsigned char>(c)) != 0;
}

bool isAlnum(char c) {
    return std::isalnum(static_cast<unsigned char>(c)) != 0;
}

bool checkpass(std::string pass) {
    bool valid = true;

    if (pass.size() < 8) {
        std::cout << "Too short" << std::endl;
        valid = false;
    }

    size_t distinctChars = 0;
    for (size_t i = 0; i < pass.size(); ++i) {
        if (isAlnum(pass[i]) || !isAlpha(pass[i]) && !isDigit(pass[i])) {
            if (pass.find(pass[i], i + 1) == std::string::npos) {
                distinctChars++;
            }
        }
    }

    if (distinctChars < 6) {

        std::cout << "Too few different characters" << std::endl;
        valid = false;
    }

    bool hasDigit = false, hasUpper = false, hasLower = false;
    for (char c : pass) {
        if (isDigit(c)) {
            hasDigit = true;
        } else if (isAlpha(c)) {
            if (std::islower(static_cast<unsigned char>(c)) != 0) {
                hasLower = true;
            } else {
                hasUpper = true;
            }
        }
    }

    if (!hasDigit) {
        std::cout << "No digit" << std::endl;
        valid = false;
    }
    if (!hasUpper) {
        std::cout << "No uppercase letter" << std::endl;
        valid = false;
    }
    if (!hasLower) {
        std::cout << "No lowercase letter" << std::endl;
        valid = false;
    }

    bool hasNonAlnum = false;
    for (char c : pass) {
        if (!isAlnum(c)) {
            hasNonAlnum = true;
            break;
        }
    }

    if (!hasNonAlnum) {
        std::cout << "No non-alphanumeric character" << std::endl;
        valid = false;
    }

    return valid;
}

int main() {
    using std::cout; using std::endl;
    const std::string passes[] =
    {
        "AbcDe93", "A1b:A1b>", "Ab:Acb<",
        "abc123><", "Zorro@123"
    };
    for (size_t i = 0; i < std::size(passes); ++i) {
        cout << "checking " << passes[i] << endl;
        if (checkpass(passes[i])) cout << "OK" << endl;
        cout << endl;
    }

    return 0;
}