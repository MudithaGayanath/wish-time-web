export  default function  PageNotFound(){
    document.title='404 | Page not found';
    return (
            <div className="flex w-full h-screen items-center justify-center bg-gray-900 m-0 p-0  ">
                <div className="text-center ">
                    <p className="text-base font-semibold text-indigo-400">404</p>
                    <h1 className="  text-balance text-5xl font-semibold tracking-tight text-white sm:text-7xl">
                        Page not found
                    </h1>
                    <p className="mt-6 text-pretty text-lg font-medium text-gray-400 sm:text-xl/8">
                        Sorry, we couldn’t find the page you’re looking for.
                    </p>
                    <div className="mt-10 flex items-center justify-center gap-4  ">
                        <a
                            href="#"
                            className="rounded-md bg-indigo-500 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-500"
                        >
                            Go back home
                        </a>
                        <a href="#" className="text-sm font-semibold text-white">
                            Contact support <span aria-hidden="true">&rarr;</span>
                        </a>
                    </div>
                </div>
            </div>
    )
}