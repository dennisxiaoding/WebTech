from django.http import HttpResponse

# Create your views here.


def hello(request):
    return HttpResponse("<p>The dog is barking: hello world!</p>")
