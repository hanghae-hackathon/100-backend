import sys
import json

def process_data(data):
    # JSON 문자열을 딕셔너리로 변환
    data_dict = json.loads(data)
    # AI 모델 처리 로직 (여기서는 간단히 출력)
    print("Processing data for dog:", data_dict)

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python model.py '<json_data>'")
    else:
        process_data(sys.argv[1])
