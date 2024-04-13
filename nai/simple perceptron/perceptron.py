import numpy as np
import csv


class Perceptron:
    def __init__(self, learning_rate=0.01, n_iterations=1000):
        self.learning_rate = learning_rate
        self.n_iterations = n_iterations
        self.weights = None
        self.bias = None

    def train(self, X, y):
        n_samples, n_features = X.shape
        self.weights = np.random.rand(n_features)
        self.bias = np.random.rand()

        for _ in range(self.n_iterations):
            for i in range(n_samples):
                y_predicted = self.predict(X[i])
                update = self.learning_rate * (y[i] - y_predicted)
                self.weights += update * X[i]
                self.bias += update

    def predict(self, x):
        linear_output = np.dot(x, self.weights) + self.bias
        return 1 if linear_output >= 0 else 0


def load_dataset(filename):
    X = []
    y = []
    with open(filename, 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            features = [float(value) for value in row[:-1]]
            label = row[-1]
            X.append(features)
            y.append(1 if label == 'Iris-versicolor' else 0)
    return np.array(X), np.array(y)


def test_model(model, X_test, y_test):
    n_samples = X_test.shape[0]
    correct = 0
    for i in range(n_samples):
        print(f'predict: {model.predict(X_test[i])}; answer: {y_test[i]}')
        if model.predict(X_test[i]) == y_test[i]:
            correct += 1
    accuracy = correct / n_samples
    return accuracy


def get_input_vector():
    vector_str = input("Enter vector (comma-separated values): ")
    vector = [float(x.strip()) for x in vector_str.split(',')]
    return vector


def classify_input(perceptron_model):
    input_vector = get_input_vector()
    prediction = perceptron_model.predict(input_vector)
    class_name = "Iris-versicolor" if prediction == 1 else "Iris-virginica"
    print("Predicted class:", class_name)


if __name__ == "__main__":
    X_train, y_train = load_dataset("perceptron_data.csv")

    perceptron = Perceptron(learning_rate=0.1, n_iterations=100)
    perceptron.train(X_train, y_train)

    X_test, y_test = load_dataset("perceptron_test_data.csv")

    accuracy = test_model(perceptron, X_test, y_test)
    print(f"Accuracy: {accuracy}")

    while True:
        classify_input(perceptron)
        choice = input("Do you want to classify another vector? (y/n): ")
        if choice.lower() != 'y':
            break

