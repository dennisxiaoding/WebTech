from django.http import HttpResponse

# Create your views here.


#def hello(request):
#    return HttpResponse("<p>The dog is barking: hello world!</p>")
from django.shortcuts import render


def hello(request):
    context = {}
    context['hello'] = 'Hello world!(render)'
    return render(request, 'hello.html', context)
