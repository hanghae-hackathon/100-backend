import json
import sys

def main(data):
    try:
        print(f"Raw input data: {data}")
        if not data:
            raise ValueError("Input data is empty")
        data_dict = json.loads(data)
        # 데이터 처리 로직 추가
        return data_dict
    except (json.JSONDecodeError, ValueError) as e:
        print(f"Error processing input data: {e}", file=sys.stderr)
        return None

if __name__ == "__main__":
    input_data = sys.stdin.read()
    if not input_data:
        print("No input data provided")
    else:
        result = main(input_data)
        if result:
            print("Data processed successfully:", result)
        else:
            print("Failed to process data")
