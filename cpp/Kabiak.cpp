#include <iostream>
using namespace std;


int sum_of_digits(int num) {
    int sum = 0;
    while (num > 0) {
        sum += num % 10;
        num /= 10;
    }
    return sum;
}

int main() {
    int num, max_sum_of_digits = 0, max_num = 0;

    while (true) {
        cout << "enter a natural number (0 if done):";

        cin >> num;

        if (num == 0) {
            break;
        }

        int current_sum = sum_of_digits(num);
        if (current_sum > max_sum_of_digits) {
            max_sum_of_digits = current_sum;
            max_num = num;
        }
    }

    cout << "Max sum of digits was " << max_sum_of_digits << " for " << max_num;

    return 0;
}