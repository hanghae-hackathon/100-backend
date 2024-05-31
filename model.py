import sys
import json

def main(data):
    # Convert string -> dictionary
    data_dict = json.loads(data)

    # Model processing logic
    print("Processing data for:", data_dict["name"])

    # Return
    return "Processed"

if __name__ == "__main__":
    if len(sys.argv) > 1:
        input_data = sys.argv[1]
        result = main(input_data)
        print(result)
    else:
        print("No input data provided")
