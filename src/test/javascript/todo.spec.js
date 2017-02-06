/**
 * Created by blask017 on 2/6/17.
 */
describe('testing client side functionality', function(){

    it('shoould return where the owner is not blank',function () {
        expect(urlBuilder("","Blanche","","","","","")).toEqual("/api/todos?owner=Blanche&contains=&category=&orderBy=");
    })


    it('shoould return where the status is complete',function () {
        expect(urlBuilder("","","complete","","","","")).toEqual("/api/todos?owner=&contains=&category=&orderBy=&status=complete");
    })


    it('shoould return where the limit is 5',function () {
        expect(urlBuilder("","","","","","5","")).toEqual("/api/todos?owner=&contains=&category=&orderBy=&limit=5");
    })


    it('shoould return where the id is 588959852a278361a5ea251a',function () {
        expect(urlBuilder("588959852a278361a5ea251a","","","","","","")).toEqual("/api/todos/588959852a278361a5ea251a");
    })


    it('shoould return where the body contains voluptate',function () {
        expect(urlBuilder("","","","voluptate","","","")).toEqual("/api/todos?owner=&contains=voluptate&category=&orderBy=");
    })


    it('shoould return where the category is homework',function () {
        expect(urlBuilder("","","","","homework","","")).toEqual("/api/todos?owner=&contains=&category=homework&orderBy=");
    })


    it('shoould return where orderBy is not blank',function () {
        expect(urlBuilder("","","","","","","body")).toEqual("/api/todos?owner=&contains=&category=&orderBy=body");
    })

    it('shoould return a combination url',function () {
        expect(urlBuilder("","","incomplete","","","10","")).toEqual("/api/todos?owner=&contains=&category=&orderBy=&status=incomplete&limit=10");
    })


    it('shoould return a combination url',function () {
        expect(urlBuilder("","Barry","","","video games","","")).toEqual("/api/todos?owner=Barry&contains=&category=video games&orderBy=");
    })

    it('shoould return a combination url',function () {
        expect(urlBuilder("588959851caac57352b9ffea","","incomplete","","","10","")).toEqual("/api/todos/588959851caac57352b9ffea");
    })


    it('shoould return a combination url',function () {
        expect(urlBuilder("","Fry","incomplete","dolor","software design","10","owner")).toEqual("/api/todos?owner=Fry&contains=dolor&category=software design&orderBy=owner&status=incomplete&limit=10");
    })

});

