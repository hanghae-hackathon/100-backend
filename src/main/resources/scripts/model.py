import json
import sys

def main(data, video_file):
    try:
        print(f"Raw input data: {data}")
        print(f"Video file to process: {video_file}")
        if not data:
            raise ValueError("Input data is empty")
        data_dict = json.loads(data)
        # 비디오 파일 처리 로직 추가
        return data_dict
    except (json.JSONDecodeError, ValueError) as e:
        print(f"Error processing input data: {e}", file=sys.stderr)
        return None

if __name__ == "__main__":
    input_data = sys.stdin.read()
    if len(sys.argv) < 2:
        print("No video file provided")
        sys.exit(1)

    video_file = sys.argv[1]

    if not input_data:
        print("No input data provided")
    else:
        result = main(input_data, video_file)
        if result:
            print("Data processed successfully:", result)
        else:
            print("Failed to process data")
