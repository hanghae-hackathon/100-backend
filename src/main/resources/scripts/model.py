
# 파이썬으로 input을 비디오 url을 보내면 return값으로 description (String을 받아)
import sys

def get_description(video_url):
    # Sring
    return "이것은 비디오입니다."

if __name__ == "__main__":
    # 표준 입력으로 URL
    video_url = sys.stdin.read().strip()

    # 비디오 URL에 대한 설명
    description = get_description(video_url)

    # 결과를 단순 String 형태
    print(description)
