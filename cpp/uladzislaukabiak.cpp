#include <iostream>
using namespace std;

int main() {
    int num;
    int min_value = INT_MAX;
    int max_value = INT_MIN;
    int min_count = 0;
    int max_count = 0;

    while (true) {
        cin >> num;

        if (num == 0) {
            break;
        }

        if (num < min_value) {
            min_value = num;
            min_count = 1;
        } else if (num == min_value) {
            min_count++;
        }

        if (num > max_value) {
            max_value = num;
            max_count = 1;
        } else if (num == max_value) {
            max_count++;
        }
    }

    if (min_value != INT_MAX) {
        cout << "Min: " << min_value << " (" << min_count << ")" << endl;
    } else {
        cout << "No valid input." << endl;
    }

    if (max_value != INT_MIN) {
        cout << "Max: " << max_value << " (" << max_count << ")" << endl;
    } else {
        cout << "No valid input." << endl;
    }

    return 0;
}