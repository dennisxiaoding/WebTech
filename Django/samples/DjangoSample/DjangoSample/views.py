from django.http import HttpResponse

__author__ = 'canbin.zhang'


def hello(request):
    return HttpResponse('<p> hello world! </p>')